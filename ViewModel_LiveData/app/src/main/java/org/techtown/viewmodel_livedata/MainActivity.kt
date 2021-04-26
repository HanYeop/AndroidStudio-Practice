package org.techtown.viewmodel_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mainViewModel : MainViewModel
    lateinit var button1 : Button
    lateinit var button2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = findViewById<TextView>(R.id.numberText)

        button1 = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button1.setOnClickListener (this)
        button2.setOnClickListener (this)

        // 뷰모델 가져오기
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // 관찰하여 데이터 값이 변경되면 호출
        mainViewModel.currentValue.observe(this, Observer {
            text.text = it.toString()
        })

    }

    override fun onClick(p0: View?) {
        when(p0){
            button1 ->
                mainViewModel.updateValue(actionType.UP)
            button2 ->
                mainViewModel.updateValue(actionType.DOWN)
        }
    }
}