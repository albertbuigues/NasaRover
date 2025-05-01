package com.buigues.ortola.nasarover.ui.composables.robot

import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buigues.ortola.nasarover.R
import com.buigues.ortola.nasarover.models.robot.Orientation
import com.buigues.ortola.nasarover.viewmodels.RobotViewModel
import org.koin.compose.koinInject

@Composable
fun StatelessRobot(orientation: Orientation) {
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.bip)
    }
    val rotation = when (orientation) {
        Orientation.NORTH -> 0f
        Orientation.EAST -> 90f
        Orientation.SOUTH -> 180f
        Orientation.WEST -> 270f
    }
    val animatedRotation = remember { Animatable(rotation) }

    // This is executed when rotation is performed
    LaunchedEffect(rotation) {
        val current = animatedRotation.value % 360f
        val target = rotation % 360f

        var delta = (target - current + 360f) % 360f
        if (delta > 180f) delta -= 360f

        val destination = animatedRotation.value + delta

        if (animatedRotation.value != destination) {
            val mediaPlayer = MediaPlayer.create(context, R.raw.bip)
            mediaPlayer.isLooping = true
            mediaPlayer.start()
            animatedRotation.animateTo(
                targetValue = destination,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = LinearEasing
                )
            )
            mediaPlayer.stop()
        }
    }

    Box(Modifier.size(24.dp)) {
        Image(
            painter = painterResource(R.drawable.robot),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .rotate(animatedRotation.value % 360f)
        )
    }

    // this is executed when composable is removed from tree
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }
}


@Composable
fun StatefulRobot(viewModel: RobotViewModel = koinInject<RobotViewModel>()) {
    val robotState by remember { viewModel.robotState }
    StatelessRobot(robotState.orientation)
}

@Preview
@Composable
private fun PreviewStatelessRobot() {
    StatefulRobot()
}