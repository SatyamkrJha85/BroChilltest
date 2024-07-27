package com.theapplicationpad.brochilltest.Mvvm.Model

data class TweetResponse(
    val tweets: List<Tweet>
)

data class Tweet(
    val tweet: String? = null,
    val _id: String? = null,
    val __v: String? = null
)