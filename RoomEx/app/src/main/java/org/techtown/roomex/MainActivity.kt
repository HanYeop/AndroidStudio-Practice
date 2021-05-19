package org.techtown.roomex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.techtown.roomex.databinding.ActivityMainBinding
import org.techtown.roomex.dialog.CustomDialog
import org.techtown.roomex.dialog.CustomDialogInterface

class MainActivity : AppCompatActivity(), CustomDialogInterface {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

    // Fab 클릭시
    fun onFabClicked(view : View){
        val customDialog = CustomDialog(this,this)
        customDialog.show()
    }


    override fun onAddButtonClicked(name : String, age : Int) {
        Toast.makeText(this, "이름 : $name , 나이 : $age 추가", Toast.LENGTH_SHORT).show()
    }

    override fun onCancelButtonClicked() {
        Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show()
    }
}