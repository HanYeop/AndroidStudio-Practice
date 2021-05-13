package org.techtown.retrofit_test.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techtown.retrofit_test.model.Post
import org.techtown.retrofit_test.repository.Repository

class MainViewModel(private val repository : Repository) : ViewModel() {

    val myResponse : MutableLiveData<Post> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
}