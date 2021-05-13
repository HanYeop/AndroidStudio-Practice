package org.techtown.roomtest

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user : User)

    @Update
    fun update(user : User)

    @Delete
    fun delete(user : User)

    @Query("SELECT * FROM User")
    fun getAll() : LiveData<List<User>>

    @Query("DELETE FROM User ")
    fun deleteAll()
}