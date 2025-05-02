package com.buigues.ortola.nasarover.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.buigues.ortola.nasarover.models.Coordinates
import com.buigues.ortola.nasarover.models.robot.Orientation
import com.buigues.ortola.nasarover.models.robot.RobotState

class RobotViewModel: ViewModel() {

    private var mRobotState = mutableStateOf(RobotState())
    val robotState: State<RobotState> = mRobotState

    fun updateState(newState: RobotState) {
        mRobotState.value = newState
    }

    fun rotateRight() {
        val currentOrientation = this.mRobotState.value.orientation
        val currentCoordinates = this.mRobotState.value.coordinates
        when (currentOrientation) {
            Orientation.NORTH -> this.mRobotState.value = RobotState(
                orientation = Orientation.EAST, coordinates = currentCoordinates
            )
            Orientation.EAST -> this.mRobotState.value = RobotState(
                orientation = Orientation.SOUTH, coordinates = currentCoordinates
            )
            Orientation.SOUTH -> this.mRobotState.value = RobotState(
                orientation = Orientation.WEST,
                coordinates = currentCoordinates
            )
            Orientation.WEST -> this.mRobotState.value = RobotState(
                orientation = Orientation.NORTH,
                coordinates = currentCoordinates
            )
        }
    }

    fun rotateLeft() {
        val currentCoordinates = this.mRobotState.value.coordinates
        val currentOrientation = this.mRobotState.value.orientation
        when (currentOrientation) {
            Orientation.NORTH -> this.mRobotState.value = RobotState(
                orientation = Orientation.WEST, coordinates = currentCoordinates
            )
            Orientation.EAST -> this.mRobotState.value = RobotState(
                orientation = Orientation.NORTH, coordinates = currentCoordinates
            )
            Orientation.SOUTH -> this.mRobotState.value = RobotState(
                orientation = Orientation.EAST, coordinates = currentCoordinates
            )
            Orientation.WEST -> this.mRobotState.value = RobotState(
                orientation = Orientation.SOUTH, coordinates = currentCoordinates
            )
        }
    }

    fun canMoveForward(gridRows: Int, gridColumns: Int): Boolean {
        return when(this.mRobotState.value.orientation) {
            Orientation.NORTH -> this.mRobotState.value.coordinates.y < gridRows - 1
            Orientation.EAST -> this.mRobotState.value.coordinates.x < gridColumns - 1
            Orientation.WEST -> this.mRobotState.value.coordinates.x > 0
            Orientation.SOUTH -> this.mRobotState.value.coordinates.y > 0
        }
    }

    fun moveForward() {
        val currentOrientation = this.mRobotState.value.orientation
        val currentCoordinates = this.mRobotState.value.coordinates
        when(currentOrientation) {
            Orientation.NORTH -> updateState(RobotState(
                orientation = currentOrientation,
                coordinates = Coordinates(currentCoordinates.x, currentCoordinates.y + 1)
            ))
            Orientation.EAST -> updateState(RobotState(
                orientation = currentOrientation,
                coordinates = Coordinates(currentCoordinates.x + 1, currentCoordinates.y)
            ))
            Orientation.WEST -> updateState(RobotState(
                orientation = currentOrientation,
                coordinates = Coordinates(currentCoordinates.x - 1, currentCoordinates.y)
            ))
            Orientation.SOUTH -> updateState(RobotState(
                orientation = currentOrientation,
                coordinates = Coordinates(currentCoordinates.x, currentCoordinates.y - 1)
            ))
        }
    }
}