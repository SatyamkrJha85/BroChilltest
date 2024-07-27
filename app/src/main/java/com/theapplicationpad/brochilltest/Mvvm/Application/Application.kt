package com.theapplicationpad.brochilltest.Mvvm.Application


import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

class MyApplication : Application() {
    // Create a DataStore instance
    val dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")
}
