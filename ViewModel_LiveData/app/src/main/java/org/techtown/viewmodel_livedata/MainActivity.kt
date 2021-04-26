package org.techtown.viewmodel_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.techtown.viewmodel_livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel : MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // 뷰모델 가져오기
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.mainViewModel = mainViewModel

        // 관찰하여 데이터 값이 변경되면 호출
        mainViewModel.currentValue.observe(this, Observer {
            Log.d("check","현재 값 : $it")
            binding.numberText.text = it.toString()
        })
        mainViewModel.currentValue2.observe(this, Observer {
            binding.numberText2.text = it.toString()
        })
    }
}