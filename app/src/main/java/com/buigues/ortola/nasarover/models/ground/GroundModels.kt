package com.buigues.ortola.nasarover.models.ground

import com.buigues.ortola.nasarover.models.Coordinates

data class GroundCellState(
    val associatedCoordinates: Coordinates = Coordinates(0, 0),
    val isRobotInside: Boolean = false
)

data class GroundGridState(
    val rows: Int = 0,
    val columns: Int = 0
)