package com.github.dragon925.screensexample.utils

import com.github.dragon925.screensexample.domain.item.EventItem
import com.github.dragon925.screensexample.domain.model.Event
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


val timeFormat = SimpleDateFormat("HH:mm", Locale("ru"))
fun Event.toEventItem(): EventItem {
    val time = this.time
    val date = if (time == null) {
        Date()
    } else {
        Date(time)
    }
    return EventItem(
        id,
        timeFormat.format(date),
        name ?: "Unknown"
    )
}