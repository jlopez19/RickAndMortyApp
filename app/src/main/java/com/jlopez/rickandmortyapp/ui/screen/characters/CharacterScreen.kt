package com.jlopez.rickandmortyapp.ui.screen.characters

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.jlopez.rickandmortyapp.domain.model.Characters
import com.jlopez.rickandmortyapp.ui.screen.components.AsyncImageWithPreview
import com.jlopez.rickandmortyapp.ui.screen.components.ErrorScreen
import com.jlopez.rickandmortyapp.ui.screen.components.LoadingScreen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CharacterScreen (onCharacterClick: (Int) -> Unit,
                     viewModel: CharacterViewModel = hiltViewModel()){
    val state = viewModel.state.value

    if (state.isLoading) {
        LoadingScreen()
    } else {
        state.error?.let {
            ErrorScreen(errorMessage = it)
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            state.characters?.let {
                items(it.size) { index ->
                    CharacterListItem(state.characters[index], onCharacterClick)
                }
            }
        }
    }
}

@Composable
fun CharacterListItem(character: Characters, onCharacterClick: (Int) -> Unit) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()
    val request = ImageRequest.Builder(LocalContext.current)
        .data(character.image)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCharacterClick(character.id) },
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImageWithPreview(
                model = character.image,
                contentDescription = character.name,
                modifier =Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = character.name,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
                )
            }
        }
    }
}



