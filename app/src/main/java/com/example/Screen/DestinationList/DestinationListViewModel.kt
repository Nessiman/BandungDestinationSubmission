package com.example.Screen.DestinationList

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bandungdestination.data.BandungDestinationRepository
import com.example.bandungdestination.model.DataBandungDestination
import com.example.bandungdestination.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DestinationListViewModel(private val repository: BandungDestinationRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<DataBandungDestination>>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<DataBandungDestination>>>
        get() = _uiState

    fun getDestinationList() = viewModelScope.launch {
        repository.getBandungDestinationList()
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect{
                _uiState.value = UiState.Success(it)
            }
    }

    fun bandungDestinationUpdate(id: Int, newState: Boolean){
        repository.updateBandungDestination(id, newState)
        getDestinationList()
    }
}