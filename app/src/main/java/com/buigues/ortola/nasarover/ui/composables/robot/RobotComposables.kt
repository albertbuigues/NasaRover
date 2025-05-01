package com.buigues.ortola.nasarover.ui.composables.robot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buigues.ortola.nasarover.R

@Composable
fun StatelessRobot() {
    Box(Modifier.size(40.dp)) {
        Image(
            painter = painterResource(R.drawable.robot), contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun PreviewStatelessRobot() {
    StatelessRobot()
}