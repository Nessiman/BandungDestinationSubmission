    package com.example.Screen.detail

    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.ArrowBack
    import androidx.compose.material.icons.filled.Favorite
    import androidx.compose.material.icons.filled.FavoriteBorder
    import androidx.compose.material.icons.filled.Info
    import androidx.compose.material.icons.filled.LocationOn
    import androidx.compose.material.icons.filled.ShoppingCart
    import androidx.compose.material.icons.filled.Star
    import androidx.compose.material3.Divider
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.collectAsState
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.layout.VerticalAlignmentLine
    import androidx.compose.ui.platform.testTag
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.lifecycle.viewmodel.compose.viewModel
    import com.example.bandungdestination.R
    import com.example.bandungdestination.di.Injection
    import com.example.bandungdestination.ui.ViewModelFactory
    import com.example.bandungdestination.ui.common.UiState

    @Composable
    fun DetailScreen(
        bandungDestinationId : Int,
        navigateBack: () -> Unit,
        modifier: Modifier = Modifier,
        viewModel: DetailViewModel = viewModel(
            factory = ViewModelFactory(Injection.provideRepository())
        )
    ){
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when(uiState){
                is UiState.Loading -> {
                    viewModel.getBandungDestinationById(bandungDestinationId)
                }
                is UiState.Success -> {
                    val data = uiState.data
                    DetailData(
                        id = data.id,
                        name = data.name,
                        rating = data.rating,
                        image = data.image,
                        price = data.price,
                        location = data.location,
                        deskripsi = data.deskripsi,
                        navigateBack = navigateBack,
                        onFavoriteButtonClicked = { id, state ->
                            viewModel.bandungDestinationUpdate(id, state)
                        }
                    )
                }
                is UiState.Error -> {}
            }
        }
    }


    @Composable
    fun DetailData(
        id: Int,
        name: String,
        rating: Double,
        image: Int,
        price: Int,
        location: String,
        deskripsi: String,
        isFavorite: Boolean = false,
        navigateBack: () -> Unit,
        onFavoriteButtonClicked: (id: Int, state: Boolean) -> Unit,
    ){
        Box(modifier = Modifier.fillMaxSize()){
            Column (
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 16.dp)
            ){
                Image(
                    painter = painterResource(image),
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("scrollToBottom")
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Text(
                            text = rating.toString(),
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Text(
                            text = price.toString(),
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Text(
                            text = location,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                        )
                    }
                }
                Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
                Text(
                    text = stringResource(id = R.string.deskripsi),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = deskripsi,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 28.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
            }


            IconButton(
                onClick = navigateBack,
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp)
                    .align(Alignment.TopStart)
                    .clip(CircleShape)
                    .size(40.dp)
                    .testTag("back_home")
                    .background(Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = Color.Black
                )
            }

            IconButton(
                onClick = {
                    onFavoriteButtonClicked(id, isFavorite)
                },
                modifier = Modifier
                    .padding(end = 16.dp, top = 8.dp)
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(Color.White)
                    .testTag("favorite_detail_button")
            ) {
                Icon(
                    imageVector = if (!isFavorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                    contentDescription = if (!isFavorite) stringResource(R.string.add_to_favorite) else stringResource(
                        R.string.delete),
                    tint = if (!isFavorite) Color.Black else Color.Red
                )
            }

        }
    }