package com.github.dragon925.screensexample.domain.repository

import com.github.dragon925.screensexample.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {

    fun getEvents(): Flow<List<Event>>

    suspend fun addEvent(name: String, time: Long): Long
}