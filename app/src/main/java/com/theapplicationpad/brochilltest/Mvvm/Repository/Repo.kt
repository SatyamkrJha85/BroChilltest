package com.theapplicationpad.brochilltest.Mvvm.Repository

import com.theapplicationpad.brochilltest.Mvvm.Api.ApiService
import com.theapplicationpad.brochilltest.Mvvm.Model.Tweet
import com.theapplicationpad.brochilltest.Mvvm.Model.UserModel
import retrofit2.http.Body

class Repo(private val apiService: ApiService) {


    suspend fun createUser(@Body userRegistrationModel: UserModel): UserModel {
        return apiService.userRegistration(userRegistrationModel)
    }

    suspend fun loginUser(@Body userRegistrationModel: UserModel): UserModel {
        val response = apiService.userLogin(userRegistrationModel)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("No Data Received")
        } else {
            val errorMessage = response.errorBody()?.string() ?: "Server Issues"
            throw Exception(errorMessage)
        }
    }

    suspend fun postTweet(token: String, tweet: Tweet): Tweet {
        return apiService.postTweet(token,tweet)
    }
    suspend fun tweetRes(token: String):List<Tweet>{
        return apiService.getTweets(token)
    }



}