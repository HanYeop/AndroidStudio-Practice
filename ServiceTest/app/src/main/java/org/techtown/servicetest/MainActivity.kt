package org.techtown.servicetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ServiceStart(view : View){
        val intent = Intent(this,MyService::class.java)
        startService(intent) // 서비스 시작
    }

    fun ServiceStop(view : View){
        val intent = Intent(this,MyService::class.java)
        stopService(intent) // 서비스 종료
    }
}