package com.jlopez.rickandmortyapp.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jlopez.rickandmortyapp.ui.screen.characterdetail.CharacterDetailScreen
import com.jlopez.rickandmortyapp.ui.screen.characterdetail.CharacterDetailViewModel
import com.jlopez.rickandmortyapp.ui.screen.characters.CharacterScreen
import com.jlopez.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "characterList") {
                    composable("characterList") {
                        CharacterScreen(onCharacterClick = { characterId ->
                            navController.navigate("characterDetails/$characterId")
                        })
                    }
                    composable("characterDetails/{characterId}") { backStackEntry ->
                        val characterId =
                            backStackEntry.arguments?.getString("characterId")?.toInt() ?: 0
                        CharacterDetailScreen(
                            characterId = characterId
                        )
                    }
                }
            }
        }
    }
}