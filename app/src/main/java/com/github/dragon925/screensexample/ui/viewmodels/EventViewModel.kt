package com.github.dragon925.screensexample.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.github.dragon925.screensexample.data.repository.EventRepositoryImpl
import com.github.dragon925.screensexample.domain.item.EventItem
import com.github.dragon925.screensexample.domain.model.Event
import com.github.dragon925.screensexample.domain.repository.EventRepository
import com.github.dragon925.screensexample.utils.toEventItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class EventViewModel : ViewModel() {

    private val repository: EventRepository = EventRepositoryImpl

    private val data = MutableLiveData<List<Event>>(emptyList())

    private val _events: LiveData<List<EventItem>> = data.map { list ->
        list.map { it.toEventItem() }
    }
    private val _query = MutableLiveData("")
    val events: LiveData<List<EventItem>>
        get() = MediatorLiveData<List<EventItem>>().apply {
            addSource(_events) { list ->
                val query = _query.value
                value = if (query.isNullOrBlank()) {
                    list
                } else {
                    list.filter { it.name.contains(query) }
                }
            }
            addSource(_query) { query ->
                val list = _events.value ?: emptyList()
                value = if (query.isNullOrBlank()) {
                    list
                } else {
                    list.filter { it.name.contains(query) }
                }
            }
        }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getEvents()
            withContext(Dispatchers.Main) {
                data.value = list
            }
        }
    }

    fun submitQuery(query: String?) {
        _query.value = query ?: ""
    }

    fun addEvent(name: String) {
        val time = Date().time
        val list = data.value?.toMutableList() ?: mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            val id = repository.addEvent(name, time) ?: -1
            list.add(Event(id, name, time))
            withContext(Dispatchers.Main) {
                data.value = list
            }
        }
    }
}