package com.erkindilekci.foodbook.data.local

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromAny(any: Any?): String {
        any?.let {
            return any as String
        } ?: return ""
    }

    @TypeConverter
    fun toAny(string: String?): Any {
        string?.let {
            return string
        } ?: return ""
    }
}
