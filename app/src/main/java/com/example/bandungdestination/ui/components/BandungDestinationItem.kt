package com.example.bandungdestination.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BandungDestinationItem (
    id: Int,
    name: String,
    rating: Double,
    image: Int,
    price: Int,
    isFavorite: Boolean,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .background(Color.LightGray)
            .clip(MaterialTheme.shapes.small)
            .wrapContentHeight(),
        contentAlignment = Alignment.CenterStart
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ){
          Box(
              modifier = Modifier
                  .size(120.dp)
                  .clip(shape = MaterialTheme.shapes.small)
          ){
              Image(painter = painterResource(image),
                  contentDescription = "destination image",
                  modifier = Modifier.fillMaxSize(),
                  contentScale = ContentScale.Crop
              )
              Box(
                  modifier = Modifier
                      .height(25.dp)
                      .width(120.dp)
                      .background(Color.Black.copy(alpha = 0.8f))
                      .align(Alignment.BottomStart)
              ) {
                  Text(
                      text = price.toString(),
                      color = Color.Black,
                      style = MaterialTheme.typography.bodySmall,
                      textAlign = TextAlign.Center,
                      modifier = Modifier.align(Alignment.Center)
                  )
              }
          }
          Column(
              modifier = Modifier
                  .padding(
                      start = 16.dp,
                      top = 4.dp,
                      end = 24.dp,
                      bottom = 16.dp
                  )
                  .fillMaxWidth()
          ){
              Text(
                  text = name,
                  style = MaterialTheme.typography.headlineSmall,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(end = 16.dp)
              )
              Spacer(modifier = Modifier.height(8.dp))
              Row(
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Icon(
                      imageVector = Icons.Default.Star,
                      contentDescription = null,
                      tint = Color.Blue,
                      modifier = Modifier
                          .size(16.dp)
                  )
                  Text(
                      text = rating.toString(),
                      style = MaterialTheme.typography.bodyMedium,
                      modifier = Modifier
                          .padding(start = 8.dp, end = 8.dp)
                  )
              }

              Row(
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Icon(
                      imageVector = Icons.Default.ShoppingCart ,
                      contentDescription = null,
                      tint = Color.Blue,
                      modifier = Modifier
                          .size(16.dp)
                  )
                  Text(
                      text = price.toString(),
                      style = MaterialTheme.typography.bodyMedium,
                      modifier = Modifier
                          .padding(start = 8.dp, end = 8.dp)
                  )
              }
          }
      }
        Icon(
            imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = null,
            tint = if (!isFavorite) Color.Red else Color.Red,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .size(24.dp)
                .testTag("item_watchlist_button")
                .clickable { onFavoriteIconClicked(id, !isFavorite) }
        )
    }
}
