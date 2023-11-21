package com.github.dragon925.screensexample.domain.repository

import com.github.dragon925.screensexample.domain.model.Event

interface EventRepository {

    suspend fun getEvents(): List<Event>

    suspend fun addEvent(name: String, time: Long): Int?
}