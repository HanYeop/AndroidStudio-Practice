package org.techtown.sharedpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 모드 0 = Context.MODE_PRIVATE
        val pref = this.getPreferences(0)
//        val pref = this.getSharedPreferences("a",0) // 여러개 쓸때
        val editor = pref.edit()

        // Save 키를 가진 값 불러옴
        textView2.text = pref.getString("Save","내용 없음")

        // Save 키를 가진 값 삭제
        button.setOnClickListener {
            editor.remove("Save").apply()
            textView2.text = pref.getString("Save","저장된 값 없음")
        }

        editText.addTextChangedListener(object : TextWatcher{
            // 입력 후에 작동
            override fun afterTextChanged(p0: Editable?) {
                // 텍스트가 바뀌면 Save 키에 값 넣음
                editor.putString("Save",editText.text.toString()).apply()
                // Save 키를 가진 값 불러옴
                textView2.text = pref.getString("Save","내용 없음")
            }

            // 입력 전에 작동
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            // 텍스트 변화 시 작동
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })



    }
}