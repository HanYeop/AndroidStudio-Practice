package org.techtown.recyclerviewex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.techtown.recyclerviewex.databinding.ActivityDetailViewBinding

class DetailViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩
        binding = ActivityDetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    // 클릭된 뷰에 관한 데이터를 보여줌
    fun getData(){
        if(intent.hasExtra("currentName") && intent.hasExtra("currentDes")){
            binding.textView.text = intent.getStringExtra("currentName")
            binding.textView2.text = intent.getStringExtra("currentDes")
        }
        else{
            Toast.makeText(this,"불러오기 실패",Toast.LENGTH_SHORT).show()
        }
    }

}