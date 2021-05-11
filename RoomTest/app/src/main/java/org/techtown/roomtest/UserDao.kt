package org.techtown.roomtest

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user : User)

    @Update
    suspend fun update(user : User)

    @Delete
    suspend fun delete(user : User)

    @Query("SELECT * FROM User")
    suspend fun getAll() : List<User>

    @Query("DELETE FROM User ")
    suspend fun deleteAll()
}