package com.example.Screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bandungdestination.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
){
    val image = painterResource(R.drawable.nescans)
    val name = stringResource(R.string.dev_name)
    val email = stringResource(R.string.dev_email)
    val about = stringResource(R.string.dev_about)

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = image,
                    contentDescription = "about_image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize(),
                )
            }
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = email,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = " - $about - ",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.ExtraLight,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}