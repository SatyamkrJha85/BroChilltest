package com.theapplicationpad.brochilltest.Mvvm.View
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.theapplicationpad.brochilltest.Mvvm.Application.MyApplication
import com.theapplicationpad.brochilltest.Mvvm.Datastore.DataStoreManager
import com.theapplicationpad.brochilltest.Mvvm.Model.Tweet
import com.theapplicationpad.brochilltest.Mvvm.Model.UserModel

import com.theapplicationpad.brochilltest.Mvvm.ViewModel.MainViewModel
import com.theapplicationpad.brochilltest.R
import com.theapplicationpad.brochilltest.ui.theme.Green1



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TweetsScreen(navController: NavController, viewModel: MainViewModel) {

    val context = LocalContext.current
//    val dataStore = (context.applicationContext as MyApplication).dataStore
//    val dataStoreManager = DataStoreManager(dataStore)
//
//    // Collect token state from DataStore
//    val token by dataStoreManager.userToken.collectAsState(initial = null)
//
//    // Observe the tweets from the ViewModel
//    val tweets by viewModel.getAllTweets.observeAsState(emptyList())
//
//    // Call the ViewModel function to get all tweets when the token is available
//    LaunchedEffect(token) {
//        token?.let { viewModel.allTweets(it) }
//    }





    // For own

    val tweets by viewModel.getAllTweets.observeAsState(emptyList())

//    LaunchedEffect(Unit) {
//        viewModel.allTweets("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNjZhM2MzYjMyYzQ5MTcwM2JiM2E3YWNiIiwiZW1haWwiOiJlbWFpbEB0ZXN0LmNvbSIsImlhdCI6MTcyMjA2MDg5NywiZXhwIjoxNzIyMDY4MDk3fQ.Yq9xPi8IMJDZAHhWqoYgtVf527zLkUcQpg7NThSG_7s")
//    }

    LaunchedEffect(Unit) {
        viewModel.user.observe(context as LifecycleOwner){
            //Log.d("@@@@",it.token.toString())
            viewModel.allTweets(it.token.toString())
        }
       // viewModel.allTweets(viewModel.user.to)
    }
//    viewModel.user.observe(context as LifecycleOwner){
//        Log.d("@@@@",it.token.toString())
//    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Home Screen") })
        },
        floatingActionButton = {
            FloatingActionButton(containerColor = Green1, onClick = {
                navController.navigate("addtweets")
            }, shape = CircleShape) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null, tint = Color.White)
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn {
                    items(tweets) { tweet ->
//                        Text(text = tweet.tweet ?: "", style = MaterialTheme.typography.titleMedium)
//                        Spacer(modifier = Modifier.height(8.dp))
                        TweetItem(tweet = tweet)
                        Divider()
                    }
                }
            }
        }
    )
}