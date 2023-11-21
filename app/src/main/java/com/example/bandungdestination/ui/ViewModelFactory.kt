package com.example.bandungdestination.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.Screen.DestinationList.DestinationListViewModel
import com.example.Screen.HomeScreenViewModel
import com.example.Screen.detail.DetailViewModel
import com.example.bandungdestination.data.BandungDestinationRepository

class ViewModelFactory(private val repository: BandungDestinationRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECHKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)){
            return HomeScreenViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DestinationListViewModel::class.java)){
            return DestinationListViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown ViewModelclass " +modelClass)
    }
}