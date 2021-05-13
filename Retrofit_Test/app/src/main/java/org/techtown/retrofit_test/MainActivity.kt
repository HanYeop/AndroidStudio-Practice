package org.techtown.retrofit_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {
            Log.d("Response",it.myUserId.toString())
            Log.d("Response",it.id.toString())
            Log.d("Response",it.title)
            Log.d("Response",it.body)
        })
    }
}