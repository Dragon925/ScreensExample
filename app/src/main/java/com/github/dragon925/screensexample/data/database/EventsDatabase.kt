package com.github.dragon925.screensexample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.dragon925.screensexample.data.dao.EventsDao
import com.github.dragon925.screensexample.domain.model.Event

@Database(
    version = 1,
    entities = [Event::class]
)
abstract class EventsDatabase : RoomDatabase() {

    abstract fun getEventsDao(): EventsDao
}