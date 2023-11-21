package com.example.Screen.DestinationList

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Screen.BandungDestinationList
import com.example.bandungdestination.R
import com.example.bandungdestination.di.Injection
import com.example.bandungdestination.model.DataBandungDestination
import com.example.bandungdestination.ui.ViewModelFactory
import com.example.bandungdestination.ui.common.UiState
import com.example.bandungdestination.ui.components.EmptyList

@Composable
fun DestinationList (
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DestinationListViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading ->{
                viewModel.getDestinationList()
            }
            is UiState.Success -> {
                DestinationListData(
                    listBandungDestination = uiState.data,
                    navigateToDetail = navigateToDetail,
                    onFavoriteIconClicked = { id, newState ->
                        viewModel.bandungDestinationUpdate(id, newState)
                    }
                )
            }
            else -> {}
        }

    }
}

@Composable
fun DestinationListData(
    listBandungDestination: List<DataBandungDestination>,
    navigateToDetail: (Int) -> Unit,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
){
    Column (
        modifier = Modifier
    ){
        if (listBandungDestination.isNotEmpty()){
            BandungDestinationList(
                listBandungDestination = listBandungDestination,
                onFavoriteIconClicked = onFavoriteIconClicked,
                navigateToDetail = navigateToDetail
            )
        }else{
            EmptyList(
                warning = stringResource(R.string.empty_watch_list)
            )
        }
    }
}