package com.buigues.ortola.nasarover

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.buigues.ortola.data.JsonLocalFileReader
import com.buigues.ortola.nasarover.models.ground.GroundGridState
import com.buigues.ortola.nasarover.models.robot.Orientation
import com.buigues.ortola.nasarover.models.robot.RobotState
import com.buigues.ortola.nasarover.ui.composables.ground.StatefulGroundGrid
import com.buigues.ortola.nasarover.ui.theme.NasaRoverTheme
import com.buigues.ortola.nasarover.viewmodels.AppViewModel
import com.buigues.ortola.nasarover.viewmodels.GroundGridViewModel
import com.buigues.ortola.nasarover.viewmodels.RobotViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appViewModel by inject<AppViewModel>()
            val robotViewModel by inject<RobotViewModel>()
            val context = LocalContext.current
            val filePickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.OpenDocument()
            ) { uri: Uri? ->
                uri?.let {
                    context.contentResolver.takePersistableUriPermission(
                        it, Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                    lifecycleScope.launch {
                        val reader = JsonLocalFileReader()
                        reader.readJson(context, it)?.let { instructions ->
                            appViewModel.updateState(instructions)
                        }
                    }
                }
            }
            NasaRoverTheme {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
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
                        appViewModel.instructionsState.value?.let { instructions ->
                            gridViewModel.updateState(
                                GroundGridState(
                                    rows = instructions.topRightCorner.y,
                                    columns = instructions.topRightCorner.x
                                )
                            )
                            robotViewModel.updateState(
                                RobotState(
                                    coordinates = instructions.roverPosition,
                                    orientation = when(instructions.roverDirection) {
                                        "N" -> Orientation.NORTH
                                        "E" -> Orientation.EAST
                                        "S" -> Orientation.SOUTH
                                        "W" -> Orientation.WEST
                                        else -> {
                                            Toast.makeText(context, "Invalid instructions", Toast.LENGTH_SHORT).show()
                                            Orientation.NORTH
                                        }
                                    }
                                )
                            )
                            StatefulGroundGrid()
                            lifecycleScope.launch(Dispatchers.Main) {
                                Toast.makeText(context, "Loading instructions...", Toast.LENGTH_SHORT).show()
                                delay(2000)
                                val validMovements = instructions.movements.filter { m -> m == 'L' || m == 'R' || m == 'M' }
                                validMovements.forEach { m ->
                                    when (m) {
                                        'L' -> {
                                            robotViewModel.rotateLeft()
                                            delay(1000)
                                        }
                                        'R' -> {
                                            robotViewModel.rotateRight()
                                            delay(1000)
                                        }
                                        'M' -> {
                                            if (robotViewModel.canMoveForward(
                                                    gridViewModel.groundGridState.value.rows,
                                                    gridViewModel.groundGridState.value.columns
                                            )) {
                                                robotViewModel.moveForward()
                                                delay(1000)
                                            } else {
                                                Toast.makeText(context, "Signal with Nasa lost", Toast.LENGTH_SHORT).show()
                                                return@launch
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(bottom = 24.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(onClick = {
                            filePickerLauncher.launch(arrayOf("application/json"))
                        }) {
                            Text("Cargar instrucciones")
                        }
                    }
                }
            }
        }
    }
}