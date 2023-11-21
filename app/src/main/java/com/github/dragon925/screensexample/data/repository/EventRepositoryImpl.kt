package com.github.dragon925.screensexample.data.repository

import com.github.dragon925.screensexample.domain.model.Event
import com.github.dragon925.screensexample.domain.repository.EventRepository
import kotlinx.coroutines.delay
import java.util.Date

object EventRepositoryImpl : EventRepository {

    private var _id = 0

    private val events = mutableListOf<Event>()

    init {
        val time = Date().time
        val minutes = 30*1000*60
        for (i in 0..< 10) {
            events.add(Event(_id++, "$i", time + i * minutes))
        }
    }

    override suspend fun getEvents(): List<Event> {
        delay(5000)
        return get()
    }

    override suspend fun addEvent(name: String, time: Long): Int? {
        return set(name, time)
    }

    @Synchronized private fun get(): List<Event> = events

    @Synchronized private fun set(name: String, time: Long): Int {
        val id = _id++
        events.add(Event(id, name, time))
        return id
    }
}