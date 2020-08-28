package dev.ggomes.quotations.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.ggomes.quotations.QuotationsApplication
import dev.ggomes.quotations.data.db.dao.QuotesDao
import dev.ggomes.quotations.data.db.dao.UserDao
import dev.ggomes.quotations.models.Quote
import dev.ggomes.quotations.models.UserSession

@Database(entities = arrayOf(UserSession::class, Quote::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun quotesDao(): QuotesDao
    abstract fun sessionDao(): UserDao

    companion object {
        val database by lazy {
            Room.databaseBuilder(
                QuotationsApplication.instance.applicationContext,
                AppDatabase::class.java,
            "quotations-database").build()
        }
    }
}