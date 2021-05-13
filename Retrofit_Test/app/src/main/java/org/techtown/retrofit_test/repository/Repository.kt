package org.techtown.retrofit_test.repository

import org.techtown.retrofit_test.api.RetrofitInstance
import org.techtown.retrofit_test.model.Post

class Repository {

    suspend fun getPost() : Post {
        return RetrofitInstance.api.getPost()
    }
}