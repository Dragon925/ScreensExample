package com.github.dragon925.screensexample.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.github.dragon925.screensexample.data.dao.EventsDao
import com.github.dragon925.screensexample.data.repository.EventRepositoryImpl
import com.github.dragon925.screensexample.domain.item.EventItem
import com.github.dragon925.screensexample.domain.repository.EventRepository
import com.github.dragon925.screensexample.utils.toEventItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class EventViewModel(private val repository: EventRepository) : ViewModel() {

    private val data = repository.getEvents().asLiveData()

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

    fun submitQuery(query: String?) {
        _query.value = query ?: ""
    }

    fun addEvent(name: String) {
        val time = Date().time
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEvent(name, time)
        }
    }

    class Factory(private val dao: EventsDao) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val repository = EventRepositoryImpl(dao)
            return EventViewModel(repository) as T
        }
    }
}