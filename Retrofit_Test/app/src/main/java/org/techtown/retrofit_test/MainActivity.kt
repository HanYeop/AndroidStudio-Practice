package org.techtown.retrofit_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import org.techtown.retrofit_test.databinding.ActivityMainBinding
import org.techtown.retrofit_test.repository.Repository
import org.techtown.retrofit_test.viewModel.MainViewModel
import org.techtown.retrofit_test.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰바인딩
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        binding.button.setOnClickListener {
            val myNumber = binding.editTextView.text.toString()
            viewModel.getPost2(Integer.parseInt(myNumber))

            viewModel.myResponse2.observe(this, Observer {
                if(it.isSuccessful){
                    binding.textView.text = it.body().toString()
                }
                else{
                    binding.textView.text = it.code().toString()
                }
            })
        }
    }
}