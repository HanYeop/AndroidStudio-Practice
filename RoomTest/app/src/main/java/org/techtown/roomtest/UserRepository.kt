package org.techtown.roomtest

import android.app.Application
import androidx.lifecycle.LiveData

/* 앱에서 사용하는 데이터와 그 데이터 통신을 하는 역할
뷰모델은 DB에 직접 접근하지 않아야함
 */

class UserRepository(application: Application) {
    private val userDao : UserDao
    private val userList : LiveData<List<User>>

    init{
        var db : UserDatabase = UserDatabase.getInstance(application)!!
        userDao = db.userDao()
        userList = db.userDao().getAll()
    }

    fun insert(user : User){
        userDao.insert(user)
    }

    fun update(user : User){
        userDao.update(user)
    }

    fun delete(user : User){
        userDao.delete(user)
    }

    fun getAll() : LiveData<List<User>>{
        return userDao.getAll()
    }

    fun deleteAll(){
        userDao.deleteAll()
    }
}