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
            viewModel.getCustomPosts(Integer.parseInt(myNumber))

            viewModel.myCustomPosts.observe(this, Observer {
                if(it.isSuccessful){
                    binding.textView.text = it.body().toString()
                    it.body()?.forEach{ post ->
                        Log.d("Response",post.myUserId.toString())
                        Log.d("Response",post.id.toString())
                        Log.d("Response",post.title)
                        Log.d("Response",post.body)
                        Log.d("Response","-------------------------")
                    }
                }
                else{
                    binding.textView.text = it.code().toString()
                }
            })
        }
    }
}