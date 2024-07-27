package com.theapplicationpad.brochilltest.Mvvm.Api

import com.theapplicationpad.brochilltest.Mvvm.Model.Tweet
import com.theapplicationpad.brochilltest.Mvvm.Model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    suspend fun userRegistration(@Body userRegistrationModel: UserModel): UserModel

    @POST("login")
    suspend fun userLogin(@Body userRegistrationModel: UserModel): Response<UserModel>

    @POST("tweets")
    suspend fun postTweet(
        @Header("x-api-key") token: String,
        @Body tweet: Tweet
    ): Tweet


    @GET("tweets")
    suspend fun getTweets(
        @Header("x-api-key") token: String
    ): List<Tweet>


}