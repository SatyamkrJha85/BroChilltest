package com.theapplicationpad.brochilltest.Mvvm.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.theapplicationpad.brochilltest.Mvvm.Repository.Repo
import com.theapplicationpad.brochilltest.Mvvm.ViewModel.MainViewModel

class ViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }

}

