package com.github.dragon925.screensexample.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.dragon925.screensexample.domain.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsDao {

    @Query("SELECT * FROM events")
    fun getEvents(): Flow<List<Event>>

    @Query("SELECT * FROM events WHERE id = :id")
    fun getEventById(id: Int): Event

    @Insert
    fun addEvent(event: Event): Long

    @Update
    fun updateEvent(event: Event)

    @Delete
    fun deleteEvent(event: Event)
}