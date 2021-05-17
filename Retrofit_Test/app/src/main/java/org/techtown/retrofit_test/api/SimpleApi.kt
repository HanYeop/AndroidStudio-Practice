package org.techtown.retrofit_test.api

import org.techtown.retrofit_test.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// 가능한 HTTP 동작들을 정의해놓은 인터페이스
interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost() : Response<Post>

    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number : Int
    ) : Response<Post>

    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId : Int
    ): Response<List<Post>>

    @GET("posts")
    suspend fun getCustomPost2(
        @Query("userId") userId : Int,
        @Query("_sort") sort : String,
        @Query("_order") order : String
    ): Response<List<Post>>

}