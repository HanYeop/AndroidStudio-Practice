package org.techtown.roomex.repository

import androidx.lifecycle.LiveData
import org.techtown.roomex.model.User
import org.techtown.roomex.data.UserDao

// 앱에서 사용하는 데이터와 그 데이터 통신을 하는 역할
class UserRepository(private val userDao: UserDao) {

    val readAllData : LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}