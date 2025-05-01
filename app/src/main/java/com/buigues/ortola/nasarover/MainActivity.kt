package com.buigues.ortola.nasarover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.buigues.ortola.nasarover.models.ground.GroundGridState
import com.buigues.ortola.nasarover.ui.composables.ground.StatefulGroundGrid
import com.buigues.ortola.nasarover.ui.theme.NasaRoverTheme
import com.buigues.ortola.nasarover.viewmodels.GroundGridViewModel
import com.buigues.ortola.nasarover.viewmodels.RobotViewModel
import org.koin.android.ext.android.inject
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val robotViewModel by inject<RobotViewModel>()
            NasaRoverTheme {
                Column(
                    Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(color = MaterialTheme.colorScheme.background)
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        val gridViewModel = koinInject<GroundGridViewModel>()
                        gridViewModel.updateState(
                            GroundGridState(
                                rows = 8,
                                columns = 8
                            )
                        )
                        StatefulGroundGrid()
                    }
                    Button(onClick = {
                        robotViewModel.rotateRight()
                    }) {
                        Text("Rotate right")
                    }
                    Button(onClick = {
                        robotViewModel.rotateLeft()
                    }) {
                        Text("Rotate left")
                    }
                }
            }
        }
    }
}