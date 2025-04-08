package com.jlopez.rickandmortyapp.ui.screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

sealed class Screen(val route: String) {
    object CharacterScreen: Screen("characterDetails/")
    object CharacterDetailScreen: Screen("characterDetails?characterId={characterId}"){
        fun idCharacter(id: Int): String {
            return "characterDetails?characterId=$id"
        }
    }
}

class RickAndMortyActions(navController: NavController) {
    val navigateToCharacter: () -> Unit = {
        navController.navigate(Screen.CharacterScreen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToCharacterDetail = { id: Int ->
        navController.navigate(Screen.CharacterDetailScreen.idCharacter(id)) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
        }
    }
}






