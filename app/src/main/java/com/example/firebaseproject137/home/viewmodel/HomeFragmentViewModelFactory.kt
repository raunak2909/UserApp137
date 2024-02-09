package com.example.firebaseproject137.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseproject137.data.remote.AppRepository

class HomeFragmentViewModelFactory(val appRepository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(appRepository) as T
    }
}