package org.techtown.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // lateinit 사용
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityMainBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        // 접근 가능
        binding.textView.text = "변경!"
    }

}