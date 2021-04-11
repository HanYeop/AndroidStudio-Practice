package org.techtown.firebase_test

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_result.*

class MainActivity : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    private var firestore : FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()

        // 로그아웃
        logoutbutton.setOnClickListener {
            // 로그인 화면으로
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            auth?.signOut()
        }

        saveButton.setOnClickListener {
            var resultDTO = ResultDTO()
            resultDTO.uid = auth?.currentUser?.uid
            resultDTO.textOne = writeOne.text.toString()
            resultDTO.textTwo = writeTwo.text.toString()
            resultDTO.timestamp = System.currentTimeMillis()

            firestore?.collection("Test")?.document()?.set(resultDTO)
            Toast.makeText(this,"저장완료",Toast.LENGTH_SHORT).show()
        }
    }
}