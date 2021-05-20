package org.techtown.roomex.data

import androidx.lifecycle.LiveData
import androidx.room.*
import org.techtown.roomex.model.User

@Dao
interface UserDao {

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addUser(user : User)

   @Update
   suspend fun updateUser(user : User)

   @Delete
   suspend fun deleteUser(user : User)

   @Query("SELECT * FROM user_table ORDER BY id ASC")
   fun readAllData() : LiveData<List<User>>

}