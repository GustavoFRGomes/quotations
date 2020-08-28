package dev.ggomes.quotations.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.ggomes.quotations.models.UserSession
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM usersession LIMIT 1")
    fun getUser(): Single<UserSession?>

    // This could be a Transaction to guaranteed that only one user is saved at any given time
    @Insert
    fun saveUser(userSession: UserSession): Completable

    @Delete
    fun deleteUser(userSession: UserSession): Completable
}