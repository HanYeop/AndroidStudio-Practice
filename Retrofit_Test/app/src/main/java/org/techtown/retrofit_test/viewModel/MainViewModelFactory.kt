package org.techtown.retrofit_test.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.techtown.retrofit_test.repository.Repository

// 뷰모델에 인자를 넘겨주기 위한 팩토리 메서드
class MainViewModelFactory(
    private val repository : Repository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}