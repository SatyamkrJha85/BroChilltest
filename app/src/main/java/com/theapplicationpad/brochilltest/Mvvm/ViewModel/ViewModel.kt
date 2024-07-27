package com.theapplicationpad.brochilltest.Mvvm.ViewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theapplicationpad.brochilltest.Mvvm.Model.Tweet
import com.theapplicationpad.brochilltest.Mvvm.Model.UserModel
import com.theapplicationpad.brochilltest.Mvvm.Repository.Repo
import kotlinx.coroutines.launch


class MainViewModel(private val repo: Repo) : ViewModel() {


    private val _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> get() = _user

    private val _tweets = MutableLiveData<Tweet>()
    val tweets: LiveData<Tweet> get() = _tweets


    private val _getAllTweets = MutableLiveData<List<Tweet>>()
    val getAllTweets: LiveData<List<Tweet>> get() = _getAllTweets


    fun createUser(userRegistrationModel: UserModel) {


        viewModelScope.launch {
            try {
                val data = repo.createUser(userRegistrationModel)
                _user.value = data

            } catch (e: Exception) {
                Log.d("@@@@","Getting Error"+ e.message.toString())
            }

        }
    }

    fun loginUser(userRegistrationModel: UserModel) {


        viewModelScope.launch {
            try {
                val data = repo.loginUser(userRegistrationModel)
                _user.value = data

            } catch (e: Exception) {
            }

        }
    }

    fun tweets(token: String, tweet: Tweet) {
        viewModelScope.launch {
            try {
                val data = repo.postTweet(token, tweet)
                _tweets.value = data

            } catch (e: Exception) {
                Log.d("@@@@", e.message.toString())
            }

        }
    }

    fun allTweets(token: String) {
        viewModelScope.launch {
            try {
                val data = repo.tweetRes(token)
                _getAllTweets.value = data

            } catch (e: Exception) {
                Log.d("@@@@", e.message.toString())
            }

        }
    }

}