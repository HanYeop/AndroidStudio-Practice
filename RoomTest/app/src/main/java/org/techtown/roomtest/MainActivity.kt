package org.techtown.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.techtown.roomtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // 뷰모델 가져오기. 뷰모델이 Application 파라미터를 받아야하기 때문에
        // 파라미터를 포함한 Factory 객체를 생성하여 넘겨줌.
        userViewModel = ViewModelProvider(this,UserViewModel.Factory(application)).get(UserViewModel::class.java)
        binding.userViewModel = userViewModel

        // 옵저버가 리스트의 변화를 감지
        userViewModel.getAll().observe(this, Observer {
            updateUserList(it)
        })
    }

    // 리스트를 받아서 뷰에 표시해줌
    fun updateUserList(userList : List<User>){
        var userListText = "사용자 목록"

        CoroutineScope(Dispatchers.Main).launch {
            val load = async(Dispatchers.IO) {
                for(i in userList){
                    userListText += "\n${i.id} ${i.name}, ${i.age}"
                }
            }
            load.await()
            binding.textView.text = userListText
        }
    }
}
