package com.example.Screen

import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bandungdestination.R
import com.example.bandungdestination.di.Injection
import com.example.bandungdestination.model.DataBandungDestination
import com.example.bandungdestination.ui.ViewModelFactory
import com.example.bandungdestination.ui.common.UiState
import com.example.bandungdestination.ui.components.BandungDestinationItem
import com.example.bandungdestination.ui.components.EmptyList
import com.example.bandungdestination.ui.components.SearchButton


@Composable
fun HomeScreen (
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ) ,
    navigateToDetail: (Int) -> Unit
){
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let{ uiState ->
        when(uiState){
            is UiState.Loading ->{
                viewModel.bandungDestinationSearch(query)
            }
            is UiState.Success ->{
                HomeContent(
                    query = query,
                    onQueryChange = viewModel::bandungDestinationSearch,
                    listBandungDestination = uiState.data,
                    onFavoriteIconClicked = {id, newState ->
                        viewModel.bandungDestinationUpdate(id, newState)
                    },
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listBandungDestination: List<DataBandungDestination>,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
){
    Column {
        SearchButton(query = query, onQueryChange = onQueryChange )
        if (listBandungDestination.isNotEmpty()){
            BandungDestinationList(
                listBandungDestination = listBandungDestination,
                onFavoriteIconClicked = onFavoriteIconClicked,
                navigateToDetail = navigateToDetail
            )
        }else{
            EmptyList(
                warning = stringResource(R.string.empty_list),
                modifier = Modifier
                    .testTag("emptyList")
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BandungDestinationList(
    listBandungDestination: List<DataBandungDestination>,
    onFavoriteIconClicked: (id : Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
){
    LazyColumn(
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
            top = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .testTag("lazy_list")
    ){
        items(listBandungDestination, key = { it.id }) { bandungDestination ->
            BandungDestinationItem(
                id =bandungDestination.id ,
                name =bandungDestination.name,
                rating = bandungDestination.rating ,
                image = bandungDestination.image,
                price = bandungDestination.price,
                isFavorite = bandungDestination.isFavorite,
                onFavoriteIconClicked = onFavoriteIconClicked,
                modifier = Modifier
                    .animateItemPlacement(tween(durationMillis = 500))
                    .clickable { navigateToDetail(bandungDestination.id) }
            )
        }
    }
}