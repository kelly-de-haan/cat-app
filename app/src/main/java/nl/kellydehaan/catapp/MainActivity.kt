package nl.kellydehaan.catapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.kellydehaan.catapp.data.model.CatBreed
import nl.kellydehaan.catapp.ui.screens.BreedDetailScreen
import nl.kellydehaan.catapp.ui.screens.BreedsListScreen
import nl.kellydehaan.catapp.ui.theme.CatBreedsTheme
import nl.kellydehaan.catapp.viewmodel.BreedsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatBreedsTheme {
                CatBreedsApp()
            }
        }
    }
}

@Composable
fun CatBreedsApp() {
    val navController = rememberNavController()

    val breedsViewModel: BreedsViewModel = viewModel()

    // TODO: save state in SavedInstanceState for better experience when returning from the background
    var selectedBreed by remember { mutableStateOf<CatBreed?>(null) }

    NavHost(navController = navController, startDestination = "breeds") {
        composable("breeds") {
            BreedsListScreen(
                viewModel = breedsViewModel,
                onBreedClick = { breedId ->
                    selectedBreed = breedsViewModel.uiState.value.breeds.find { it.id == breedId }
                    navController.navigate("breed_detail")
                }
            )
        }

        composable("breed_detail") {
            selectedBreed?.let { breed ->
                BreedDetailScreen(
                    breed = breed,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
