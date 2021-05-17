package org.techtown.retrofit_test.repository

import org.techtown.retrofit_test.api.RetrofitInstance
import org.techtown.retrofit_test.model.Post
import retrofit2.Response

// 뷰모델에 사용하기 위한 데이터를 통신함
class Repository {

    suspend fun getPost() : Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number : Int) : Response<Post> {
        return RetrofitInstance.api.getPost2(number)
    }
}