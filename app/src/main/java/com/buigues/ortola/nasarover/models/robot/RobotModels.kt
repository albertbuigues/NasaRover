package com.buigues.ortola.nasarover.models.robot

enum class Orientation {
    NORTH, EAST, WEST, SOUTH
}

data class Coordinates(
    val x: Int,
    val y: Int
)

data class RobotState(
    val coordinates: Coordinates = Coordinates(0, 0),
    val orientation: Orientation = Orientation.NORTH
)