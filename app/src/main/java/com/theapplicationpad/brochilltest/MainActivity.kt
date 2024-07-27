package com.theapplicationpad.brochilltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theapplicationpad.brochilltest.Mvvm.Api.APICLIENT

import com.theapplicationpad.brochilltest.Mvvm.Factory.ViewModelFactory
import com.theapplicationpad.brochilltest.Mvvm.Repository.Repo
import com.theapplicationpad.brochilltest.Mvvm.View.AddTweets
import com.theapplicationpad.brochilltest.Mvvm.View.LoginScreen
import com.theapplicationpad.brochilltest.Mvvm.View.RegisterScreen
import com.theapplicationpad.brochilltest.Mvvm.View.TweetsScreen
import com.theapplicationpad.brochilltest.Mvvm.View.WelcomeScreen
import com.theapplicationpad.brochilltest.Mvvm.ViewModel.MainViewModel
import com.theapplicationpad.brochilltest.ui.theme.BroChillTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          val  viewModel = ViewModelProvider(this,
              ViewModelFactory(repo = Repo(APICLIENT.createApiService()))
          )[MainViewModel::class.java]
            BroChillTestTheme {
                MainScreen(viewModel)
            }
        }
    }
}


@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current as ComponentActivity




    NavHost(navController = navController, startDestination = "welcome") {
        composable("register") { RegisterScreen(navController,viewModel) }
        composable("login") { LoginScreen(navController,viewModel) }
        composable("welcome") { WelcomeScreen(navController) }
        composable("tweets") { TweetsScreen(navController,viewModel) }
        composable("addtweets") { AddTweets(navController,viewModel) }
    }
}