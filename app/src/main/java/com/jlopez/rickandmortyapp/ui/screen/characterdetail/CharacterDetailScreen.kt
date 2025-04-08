package com.jlopez.rickandmortyapp.ui.screen.characterdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.style.TextOverflow
import com.jlopez.rickandmortyapp.domain.model.CharacterDetail
import com.jlopez.rickandmortyapp.ui.screen.components.ErrorScreen
import com.jlopez.rickandmortyapp.ui.screen.components.LoadingScreen
import com.jlopez.rickandmortyapp.ui.screen.components.AsyncImageWithPreview

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CharacterDetailScreen(
                            characterId: Int,
                            viewModel: CharacterDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    LaunchedEffect(characterId) {
        viewModel.getCharacterDetails(characterId)
    }

    when {
        state.isLoading -> {
            LoadingScreen()
        }
        state.character != null -> {
            val character = state.character
            CharacterDetail(character = character)
        }
        state.error != null -> {
            ErrorScreen(errorMessage = state.error)
        }
    }
}

@Composable
fun CharacterDetail(character: CharacterDetail) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AsyncImageWithPreview(
            model = character.image,
            contentDescription = character.name,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inverseOnSurface),
        )
        Column(
            modifier =
                Modifier
                    .padding(16.dp)
                    .weight(1f),
        ) {
            Text(
                text = "${character.id}",
                modifier =
                    Modifier
                        .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = character.name,
                modifier =
                    Modifier
                        .fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CharacterDetailRow("Species", character.species)
            Spacer(modifier = Modifier.height(8.dp))
            CharacterDetailRow("Type", character.type)
            Spacer(modifier = Modifier.height(8.dp))
            CharacterDetailRow("Gender", character.gender)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CharacterDetailRow( label: String,
                        value: String,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelLarge,
        )
        Text(
            text = value.ifEmpty { "N/A" },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
        )
    }

}

