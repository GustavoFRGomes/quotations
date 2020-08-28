package dev.ggomes.quotations.data.db.converters

import androidx.room.TypeConverter

object DatabaseConverters {
    @TypeConverter
    fun fromArray(items: List<String>): String {
        return items.reduce { acc, string -> "$acc,$string" }
    }

    @TypeConverter
    fun toArray(csv: String): List<String> {
        return csv.split(",")
    }
}