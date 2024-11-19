package com.bugima.onlinegallery.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.ui.featurePictureBrowser.PictureBrowserScreen
import com.bugima.onlinegallery.ui.featurePictureBrowser.PictureBrowserViewModel
import com.bugima.onlinegallery.ui.featurePictureDetails.PictureDetailsScreen
import com.bugima.onlinegallery.ui.featurePictureDetails.PictureDetailsViewModel
import com.bugima.onlinegallery.util.navigation.PictureBrowserScreen
import com.bugima.onlinegallery.util.navigation.PictureDetailsScreen
import com.bugima.onlinegallery.util.navigation.jsonType
import kotlin.reflect.typeOf

@Composable
fun RootNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PictureBrowserScreen,
    ) {
        composable<PictureBrowserScreen> {
            val viewModel: PictureBrowserViewModel = hiltViewModel()

            PictureBrowserScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                sideEffects = viewModel.sideEffects,
                navController = navController,
                onNewEvent = { event -> viewModel.onNewEvent(event) },
            )
        }

        composable<PictureDetailsScreen>(
            typeMap = mapOf(typeOf<Picture>() to jsonType<Picture>())
        ) { backStackEntry ->
            val picture = backStackEntry.toRoute<PictureDetailsScreen>().picture
            val viewModel = hiltViewModel<PictureDetailsViewModel, PictureDetailsViewModel.Factory>(
                creationCallback = { factory ->
                    factory.create(picture)
                }
            )
            PictureDetailsScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                sideEffects = viewModel.sideEffects,
                navController = navController,
                onNewEvent = { event -> viewModel.onNewEvent(event) },
            )
        }
    }
}