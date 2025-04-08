package com.jlopez.rickandmortyapp.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.jlopez.rickandmortyapp.ui.theme.RickAndMortyAppTheme

@Composable
fun RickAndMortyApp() {
    RickAndMortyAppTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            RickAndMortyActions(navController)
        }
        RickAndMortyNavGraph(
            navController = navController,
            navigateToCharacter = navigationActions.navigateToCharacter,
            navigateToCharacterDetail = navigationActions.navigateToCharacterDetail
        )

    }

}