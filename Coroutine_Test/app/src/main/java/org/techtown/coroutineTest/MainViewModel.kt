package org.techtown.coroutineTest

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _timerCount = MutableLiveData<Int>()
    private lateinit var a : Job

    // Getter
    val timerCount : LiveData<Int>
        get() = _timerCount

    init{
        _timerCount.value = 5
    }

    fun timerStart(){
        if(::a.isInitialized) a.cancel()

        _timerCount.value = 5
        a = viewModelScope.launch {
            while(_timerCount.value!! > 0) {
                _timerCount.value = _timerCount.value!!.minus(1)
                delay(1000L)
            }
        }
    }

    fun timerStop(){
        if(::a.isInitialized) a.cancel()
    }
}