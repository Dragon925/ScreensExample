package com.github.dragon925.screensexample.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dragon925.screensexample.domain.item.EventItem
import java.time.LocalTime

class EventViewModel : ViewModel() {

    private val _events = MutableLiveData<List<EventItem>>(emptyList())
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
        val time = LocalTime.of(15, 10)
        _events.value = List<EventItem>(10) {
            val value = time.plusMinutes(it * 30L)
            EventItem(it, "${value.hour}:${value.minute}", "#$it")
        }
    }

    fun submitQuery(query: String?) {
        _query.value = query ?: ""
    }

    fun addEvent(name: String) {
        val time = LocalTime.now()
        val list = _events.value?.toMutableList() ?: mutableListOf()
        list.add(EventItem(time.minute + time.hour, "${time.hour}:${time.minute}", name))
        _events.value = list
    }
}