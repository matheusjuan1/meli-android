package com.mjdev.meli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mjdev.meli.ui.routes.AppNavigation
import com.mjdev.meli.ui.theme.Gray100
import com.mjdev.meli.ui.theme.MeliTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeliTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Gray100
                ) {
                    AppNavigation()
                }
            }
        }
    }
}