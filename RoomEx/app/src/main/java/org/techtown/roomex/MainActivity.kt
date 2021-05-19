package org.techtown.roomex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.roomex.data.User
import org.techtown.roomex.data.UserViewModel
import org.techtown.roomex.databinding.ActivityMainBinding
import org.techtown.roomex.dialog.CustomDialog
import org.techtown.roomex.dialog.CustomDialogInterface

class MainActivity : AppCompatActivity(), CustomDialogInterface {
    private lateinit var binding : ActivityMainBinding
    private lateinit var userViewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // 뷰모델 연결
        userViewModel = ViewModelProvider(this,UserViewModel.Factory(application)).get(UserViewModel::class.java)

        // 아이템을 가로로 하나씩 보여줌
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        // 어댑터 연결
        val adapter = MyAdapter()
        binding.recyclerView.adapter = adapter

        userViewModel.readAllData.observe(this, Observer {
            adapter.setData(it)
        })
    }


    // Fab 클릭시 다이얼로그 띄움
    fun onFabClicked(view : View){
        val customDialog = CustomDialog(this,this)
        customDialog.show()
    }

    // 다이얼로그에서 추가버튼 클릭 됐을 때
    override fun onAddButtonClicked(name : String, age : Int) {
        val user = User(0,name,age)
        userViewModel.addUser(user)
        Toast.makeText(this, "이름 : $name , 나이 : $age 추가", Toast.LENGTH_SHORT).show()
    }

    // 다이얼로그에서 취소버튼 클릭 됐을 때
    override fun onCancelButtonClicked() {
        Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show()
    }
}