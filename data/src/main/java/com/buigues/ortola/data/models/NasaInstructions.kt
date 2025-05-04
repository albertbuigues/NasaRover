package com.buigues.ortola.data.models

import kotlinx.serialization.Serializable

@Serializable
data class NasaInstructions(
    val topRightCorner: Coordinates,
    val roverPosition: Coordinates,
    val roverDirection: String,
    val movements: String
)
