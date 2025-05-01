package com.buigues.ortola.nasarover.models.robot

import com.buigues.ortola.nasarover.models.Coordinates

enum class Orientation {
    NORTH, EAST, WEST, SOUTH
}

data class RobotState(
    val coordinates: Coordinates = Coordinates(0, 0),
    val orientation: Orientation = Orientation.NORTH
)