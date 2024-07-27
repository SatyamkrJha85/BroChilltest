package com.theapplicationpad.brochilltest.Mvvm.View
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTweets(navController: NavController, viewModel: MainViewModel) {

    var tweetText by remember { mutableStateOf("") }
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    // Use a state to hold the token
    var token by remember { mutableStateOf("") }

    // Get the DataStore instance from the application context
    val dataStore = (context.applicationContext as MyApplication).dataStore

    // Retrieve the token from DataStore
    LaunchedEffect(Unit) {
        val dataStoreManager = DataStoreManager(dataStore)
        dataStoreManager.userToken.collect { retrievedToken ->
            token = retrievedToken ?: ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Add Tweet") })
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = tweetText,
                    onValueChange = { tweetText = it },
                    label = { Text("What's on your mind?") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(modifier = Modifier
                    .width(190.dp)
                    .height(50.dp)
                    .clip(shape = RoundedCornerShape(5.dp)),onClick = {

                    if (tweetText.isNotEmpty()){

                        viewModel.user.observe(context as LifecycleOwner) {
                            viewModel.tweets(


                                token = it.token.toString(),
                                //  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNjZhM2MzYjMyYzQ5MTcwM2JiM2E3YWNiIiwiZW1haWwiOiJlbWFpbEB0ZXN0LmNvbSIsImlhdCI6MTcyMjA2MDg5NywiZXhwIjoxNzIyMDY4MDk3fQ.Yq9xPi8IMJDZAHhWqoYgtVf527zLkUcQpg7NThSG_7s",
                                Tweet(tweet = tweetText)
                            )
                        }

                        navController.navigate("tweets") {
                            popUpTo(0)
                        }

                        Toast.makeText(context, "Tweet Added", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context, "Field Empty", Toast.LENGTH_SHORT).show()

                    }


                }, colors = ButtonDefaults.buttonColors(Color.Green)) {
                    Text(text = "Tweet", color = Color.White)
                }
            }

        }

    )
}