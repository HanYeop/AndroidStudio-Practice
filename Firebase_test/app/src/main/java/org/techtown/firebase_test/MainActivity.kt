package org.techtown.firebase_test

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    private var firestore : FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // 로그아웃
        logoutbutton.setOnClickListener {
            // 로그인 화면으로
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            auth?.signOut()
        }

        // 데이터 저장
        saveButton.setOnClickListener {
            var resultDTO = ResultDTO()
            resultDTO.uid = auth?.currentUser?.uid
            resultDTO.textOne = writeOne.text.toString()
            resultDTO.textTwo = writeTwo.text.toString()
            resultDTO.timestamp = System.currentTimeMillis()

            firestore?.collection(auth!!.currentUser!!.uid)?.document()?.set(resultDTO)
            Toast.makeText(this,"저장완료",Toast.LENGTH_SHORT).show()
        }

        // 결과창으로
        resultButton.setOnClickListener {
            startActivity(Intent(this,ResultActivity::class.java))
        }
    }
}