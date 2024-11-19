package com.bugima.onlinegallery

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.navigation.RootNavigation
import com.bugima.onlinegallery.ui.featurePictureBrowser.PictureBrowserScreen
import com.bugima.onlinegallery.ui.featurePictureBrowser.PictureBrowserViewModel
import com.bugima.onlinegallery.ui.featurePictureDetails.PictureDetailsScreen
import com.bugima.onlinegallery.ui.featurePictureDetails.PictureDetailsViewModel
import com.bugima.onlinegallery.ui.theme.OnlineGalleryTheme
import com.bugima.onlinegallery.util.navigation.PictureBrowserScreen
import com.bugima.onlinegallery.util.navigation.PictureDetailsScreen
import com.bugima.onlinegallery.util.navigation.jsonType
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OnlineGalleryTheme {
                Surface {
                    RootNavigation()
                }
            }
        }
    }
}
