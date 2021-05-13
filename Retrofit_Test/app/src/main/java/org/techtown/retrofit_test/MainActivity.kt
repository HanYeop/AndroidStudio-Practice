package org.techtown.retrofit_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.techtown.retrofit_test.repository.Repository
import org.techtown.retrofit_test.viewModel.MainViewModel
import org.techtown.retrofit_test.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView : TextView = findViewById(R.id.textView)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {
            if(it.isSuccessful){
                Log.d("Response",it.body()?.myUserId.toString())
                Log.d("Response",it.body()?.id.toString())
                Log.d("Response",it.body()?.title!!)
                Log.d("Response",it.body()?.body!!)
                textView.text = it.body()?.title!!
            }
            else{
                Log.d("Response",it.errorBody().toString())
                textView.text = it.code().toString()
            }
        })
    }
}