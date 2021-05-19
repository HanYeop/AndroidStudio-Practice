package org.techtown.roomex.data

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// 뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData : LiveData<List<User>>
    private val repository : UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application)!!.userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user : User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(application) as T
        }
    }
}