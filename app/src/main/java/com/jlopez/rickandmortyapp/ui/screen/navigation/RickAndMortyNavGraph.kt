package com.jlopez.rickandmortyapp.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jlopez.rickandmortyapp.ui.screen.characterdetail.CharacterDetailScreen
import com.jlopez.rickandmortyapp.ui.screen.characters.CharacterScreen

@Composable
fun RickAndMortyNavGraph(
    modifier: Modifier = Modifier,
    navigateToCharacter: () -> Unit,
    navigateToCharacterDetail: (Int) -> Unit,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.CharacterScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.CharacterScreen.route) {
            CharacterScreen(onCharacterClick = { characterDetail ->
                navigateToCharacterDetail(characterDetail)
            })
        }
        composable(
            route = Screen.CharacterDetailScreen.route
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toInt() ?: 0
            CharacterDetailScreen(
                characterId = characterId
            )
        }
    }
}