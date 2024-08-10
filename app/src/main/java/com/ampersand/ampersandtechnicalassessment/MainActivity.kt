package com.ampersand.ampersandtechnicalassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ampersand.ampersandtechnicalassessment.ui.theme.AmpersandTechnicalAssessmentTheme
import com.ampersand.ampersandtechnicalassessment.viewmodel.MainViewModel
import com.ampersand.ampersandtechnicalassessment.screens.ListView
import com.ampersand.ampersandtechnicalassessment.screens.DetailsView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.currentBackStackEntryAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmpersandTechnicalAssessmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()
    val windowSize = rememberWindowSize()

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            NavHost(navController = navController, startDestination = "listView") {
                composable("listView") {
                    ListView(viewModel = viewModel, navController = navController)
                }
                composable("detailsView/{objectId}") { backStackEntry ->
                    val objectId = backStackEntry.arguments?.getString("objectId")
                    DetailsView(objectId = objectId, viewModel = viewModel)
                }
            }
        }
        else -> {
            Row(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f)) {
                    ListView(viewModel = viewModel, navController = navController)
                }
                Box(modifier = Modifier.weight(1f)) {
                    val currentBackStackEntry by navController.currentBackStackEntryAsState()
                    val selectedObjectId = currentBackStackEntry?.arguments?.getString("objectId")
                    DetailsView(objectId = selectedObjectId, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun rememberWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    return WindowSize(
        widthSizeClass = when {
            configuration.screenWidthDp < 600 -> WindowWidthSizeClass.Compact
            configuration.screenWidthDp < 840 -> WindowWidthSizeClass.Medium
            else -> WindowWidthSizeClass.Expanded
        }
    )
}

data class WindowSize(val widthSizeClass: WindowWidthSizeClass)

enum class WindowWidthSizeClass { Compact, Medium, Expanded }