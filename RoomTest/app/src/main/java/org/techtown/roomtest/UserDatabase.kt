package org.techtown.roomtest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// entities = 사용할 엔티티 선언, version = 엔티티 구조 변경 시 구분해주는 역할
@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object{
        private var instance : UserDatabase? = null

        @Synchronized
        fun getInstance(context : Context) : UserDatabase? {
            if(instance == null){
                synchronized(UserDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user.db"
                    ).build()
                }
            }
            return instance
        }
    }
}