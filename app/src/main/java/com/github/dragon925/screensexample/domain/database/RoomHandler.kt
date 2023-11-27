package com.github.dragon925.screensexample.domain.database

import com.github.dragon925.screensexample.data.dao.EventsDao

interface RoomHandler {

    fun getEventsDao(): EventsDao
}