package org.techtown.retrofit_test.api

import org.techtown.retrofit_test.model.Post
import retrofit2.Response
import retrofit2.http.GET

// 가능한 HTTP 동작들을 정의해놓은 인터페이스
interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost() : Response<Post>
}