package com.buigues.ortola.data.models

data class NasaInstructions(
    val topRightCorner: Pair<Int, Int>,
    val roverPosition: Pair<Int, Int>,
    val roverDirection: String,
    val movements: String
)
