package com.theapplicationpad.brochilltest.Mvvm.View

import android.util.Log
import android.widget.Toast

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.theapplicationpad.brochilltest.Mvvm.Application.MyApplication
import com.theapplicationpad.brochilltest.Mvvm.Datastore.DataStoreManager
import com.theapplicationpad.brochilltest.Mvvm.Model.UserModel

import com.theapplicationpad.brochilltest.Mvvm.ViewModel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, viewModel: MainViewModel) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    // Observe user LiveData
    val user by viewModel.user.observeAsState()

    // Execute logic when user is updated
    LaunchedEffect(user) {
        user?.let {
            Log.d("@@@@", it.token.toString())
            coroutineScope.launch {
                it.token?.let { it1 ->
                    val dataStore = (context.applicationContext as MyApplication).dataStore
                    DataStoreManager(dataStore).saveUserToken(it1)
                }
            }
        }
    }



    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Register Screen") }) },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .padding(paddingValues)
            ) {

                AppTextField(
                    value = firstName.value,
                    onValueChange = { firstName.value = it },
                    label = "First Name"
                )
                AppTextField(
                    value = lastName.value,
                    onValueChange = { lastName.value = it },
                    label = "Last Name"
                )
                AppTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = "Email"
                )
                AppTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = "Password"
                )




                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .width(190.dp)
                            .height(50.dp)
                            .clip(shape = RoundedCornerShape(5.dp)), onClick = {

                            if (firstName.value.isNotEmpty() && lastName.value.isNotEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty()) {
                                viewModel.createUser(
                                    UserModel(
                                        first_name = firstName.value,
                                        last_name = lastName.value,
                                        email = email.value,
                                        password = password.value
                                    )
                                )

                                Toast.makeText(context, "Register Sucessfull", Toast.LENGTH_SHORT).show()
                                navController.navigate("login")



                            }
                            else{

                                Toast.makeText(context, "Field", Toast.LENGTH_SHORT).show()

                            }

                            viewModel.user.observe(context as LifecycleOwner){
                                Log.d("@@@@",it.token.toString())
                            }

                        }, colors = ButtonDefaults.buttonColors(Color.Blue)
                    ) {
                        Text("Register", color = Color.White)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Already Have an account?")
                    Text(
                        text = "Login",
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navController.navigate("login")

                        })

                }

            }
        }
    )


}