package com.github.dragon925.screensexample.data.repository

import com.github.dragon925.screensexample.data.dao.EventsDao
import com.github.dragon925.screensexample.domain.model.Event
import com.github.dragon925.screensexample.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.atomic.AtomicBoolean

class EventRepositoryImpl(private val dao: EventsDao) : EventRepository {

    override fun getEvents(): Flow<List<Event>> = dao.getEvents()

    override suspend fun addEvent(name: String, time: Long): Long {
        return dao.addEvent(Event(name = name, time =  time))
    }
}