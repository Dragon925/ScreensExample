package com.github.dragon925.screensexample

import android.app.Application
import androidx.room.Room
import com.github.dragon925.screensexample.data.dao.EventsDao
import com.github.dragon925.screensexample.data.database.EventsDatabase
import com.github.dragon925.screensexample.domain.database.RoomHandler

class App : Application(), RoomHandler {

    private val room: EventsDatabase by lazy {
        Room.databaseBuilder(this, EventsDatabase::class.java, "events").build()
    }

    override fun getEventsDao(): EventsDao {
        return room.getEventsDao()
    }
}