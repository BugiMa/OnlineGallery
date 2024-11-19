package com.bugima.onlinegallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.bugima.onlinegallery.navigation.RootNavigation
import com.bugima.onlinegallery.ui.theme.OnlineGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

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
