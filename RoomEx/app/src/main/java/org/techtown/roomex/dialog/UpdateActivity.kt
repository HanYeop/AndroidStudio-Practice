package org.techtown.roomex.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.techtown.roomex.R
import org.techtown.roomex.databinding.ActivityUpdateBinding
import org.techtown.roomex.model.User
import org.techtown.roomex.viewmodel.UserViewModel

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateBinding
    private lateinit var userViewModel : UserViewModel
    private var currentId : Int? = null
    private lateinit var currentName : String
    private var currentAge : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 타이틀 삭제
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_update)

        // 뷰모델 연결
        userViewModel = ViewModelProvider(this, UserViewModel.Factory(application)).get(UserViewModel::class.java)

        // 데이터가 넘어왔을 때
        if(intent.hasExtra("currentId") && intent.hasExtra("currentName") && intent.hasExtra("currentAge")){
            // 현재 정보를 할당해줌
            currentId = Integer.parseInt(intent.getStringExtra("currentId"))
            currentName = intent.getStringExtra("currentName").toString()
            currentAge = Integer.parseInt(intent.getStringExtra("currentAge"))

            // 에디트뷰에 수정하기 전 값을 보여줌
            binding.nameEditView.setText(currentName)
            binding.ageEditView.setText(currentAge.toString())
        }
        else{
            Toast.makeText(this,"불러오기 실패", Toast.LENGTH_SHORT).show()
        }

        // 수정 버튼 클릭 시
        binding.updateBtn.setOnClickListener {

            val name = binding.nameEditView.text.toString()
            var age = binding.ageEditView.text.toString()

            // 입력하지 않았을 때
            if ( TextUtils.isEmpty(name) || TextUtils.isEmpty(age) ){
                Toast.makeText(this, "데이터를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

            // 입력 창이 비어 있지 않을 때
            else{
                val user = User(currentId!!,name,Integer.parseInt(age))
                userViewModel.updateUser(user)
                Toast.makeText(this, "이름 : $name , 나이 : $age 로 수정", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        // 취소 버튼 클릭 시
        binding.cancelBtn.setOnClickListener {
            finish()
        }

        // 삭제 버튼 클릭 시
        binding.deleteBtn.setOnClickListener {
            val user = User(currentId!!,currentName,currentAge!!)
            userViewModel.deleteUser(user)
            Toast.makeText(this, "이름 : $currentName , 나이 : $currentAge 삭제", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}