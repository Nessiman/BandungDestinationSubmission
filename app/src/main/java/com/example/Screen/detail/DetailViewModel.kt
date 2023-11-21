package com.example.Screen.detail

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bandungdestination.data.BandungDestinationRepository
import com.example.bandungdestination.model.DataBandungDestination
import com.example.bandungdestination.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: BandungDestinationRepository): ViewModel() {
    private val _uiState : MutableStateFlow<UiState<DataBandungDestination>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<DataBandungDestination>>
        get() = _uiState

    fun getBandungDestinationById(id: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getBandungDestinationById(id))
    }

    fun bandungDestinationUpdate(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateBandungDestination(id, !newState)
            .collect{ isUpdate ->
                if (isUpdate){
                    getBandungDestinationById(id)
                }
            }
    }
}