package com.buigues.ortola.nasarover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.buigues.ortola.nasarover.ui.composables.ground.StatefullGroundGrid
import com.buigues.ortola.nasarover.ui.theme.NasaRoverTheme
import com.buigues.ortola.nasarover.viewmodels.GroundGridViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NasaRoverTheme {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    StatefullGroundGrid(viewModel = GroundGridViewModel(10, 10))
                }
            }
        }
    }
}