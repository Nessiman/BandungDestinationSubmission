package com.example.bandungdestination.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bandungdestination.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchButton(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(30),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            placeholderColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .shadow(56.dp),
        placeholder = {
            Text(stringResource(id = R.string.search_text))
        }
    )
}


@Preview
@Composable
fun SearchButtonPreview() {
    SearchButton(
        query = "",
        onQueryChange = {}
    )
}