package com.example.Screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bandungdestination.data.BandungDestinationRepository
import com.example.bandungdestination.model.DataBandungDestination
import com.example.bandungdestination.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val repository: BandungDestinationRepository
) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<List<DataBandungDestination>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<DataBandungDestination>>>
        get() = _uiState
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun bandungDestinationSearch(newQuery: String) = viewModelScope.launch {
        _query.value = newQuery
        repository.SearchBandungDestination(_query.value)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect { _uiState.value = UiState.Success(it) }
    }

    fun bandungDestinationUpdate(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateBandungDestination(id, newState)
            .collect{ isUpdate ->
                if (isUpdate){
                    bandungDestinationSearch(_query.value)
                }
            }
    }
}