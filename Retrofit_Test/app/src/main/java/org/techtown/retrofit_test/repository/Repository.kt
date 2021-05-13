package org.techtown.retrofit_test.repository

import org.techtown.retrofit_test.api.RetrofitInstance
import org.techtown.retrofit_test.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost() : Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}