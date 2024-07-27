package com.theapplicationpad.brochilltest.Mvvm.Api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APICLIENT {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://wern-api.brochill.app/")
        //.client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()


    fun createApiService(): ApiService = retrofit.create(ApiService::class.java)
}