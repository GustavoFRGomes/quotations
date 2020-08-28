package dev.ggomes.quotations.data.db.dao

import androidx.room.*
import dev.ggomes.quotations.models.Quote
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface QuotesDao {
    @Query("SELECT * FROM quote")
    fun getAllQuotes(): Single<List<Quote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg quotes: Quote): Completable

    @Delete
    fun delete(quote: Quote): Completable
}