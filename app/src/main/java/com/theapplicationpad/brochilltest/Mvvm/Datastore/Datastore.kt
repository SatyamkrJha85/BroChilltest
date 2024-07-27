package com.theapplicationpad.brochilltest.Mvvm.Datastore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val USER_TOKEN_KEY = stringPreferencesKey("user_token")
    }

    // Save the user token
    suspend fun saveUserToken(token: String) {
        dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }

    // Retrieve the user token
    val userToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[USER_TOKEN_KEY]
        }
}


//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.core.stringPreferencesKey
//import androidx.datastore.preferences.preferencesDataStore
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")
//
//class DataStoreManager(private val context: Context) {
//    companion object {
//        val TOKEN_KEY = stringPreferencesKey("user_token")
//    }
//
//    val userToken: Flow<String?>
//        get() = context.dataStore.data.map { preferences ->
//            preferences[TOKEN_KEY]
//        }
//
//    suspend fun saveUserToken(token: String) {
//        context.dataStore.edit { preferences ->
//            preferences[TOKEN_KEY] = token
//        }
//    }
//
//    suspend fun clearUserToken() {
//        context.dataStore.edit { preferences ->
//            preferences.remove(TOKEN_KEY)
//        }
//    }
//}

