package org.techtown.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.coroutines.*
import org.techtown.roomtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db : UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UserDatabase.getInstance(applicationContext)!!
        fetchUserList()
    }

    fun fetchUserList(){
        var userListText = "사용자 목록"

        CoroutineScope(Dispatchers.Main).launch {

            val load = async(Dispatchers.IO) {
                val userList = db.userDao().getAll()
                for(i in userList){
                    userListText += "\n${i.id} ${i.name}, ${i.age}"
                }
            }
            load.await()
            binding.textView.text = userListText
        }
    }

    fun addUser(view : View){
        val user = User(binding.nameEditView.text.toString(),binding.ageEditView.text.toString())

        CoroutineScope(Dispatchers.IO).launch {
            db.userDao().insert(user)
        }
        fetchUserList()
    }

    fun deleteAllUser(view : View){
        CoroutineScope(Dispatchers.Main).launch {
            val delete = async(Dispatchers.IO) {
                db.userDao().deleteAll()
            }
            delete.await()
            fetchUserList()
        }
    }
}