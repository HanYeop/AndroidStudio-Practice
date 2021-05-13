package org.techtown.retrofit_test.api

import org.techtown.retrofit_test.model.Post
import retrofit2.http.GET

interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost() : Post
}