package com.buigues.ortola.nasarover.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
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
        when (currentOrientation) {
            Orientation.NORTH -> this.mRobotState.value = RobotState(orientation = Orientation.EAST)
            Orientation.EAST -> this.mRobotState.value = RobotState(orientation = Orientation.SOUTH)
            Orientation.SOUTH -> this.mRobotState.value = RobotState(orientation = Orientation.WEST)
            Orientation.WEST -> this.mRobotState.value = RobotState(orientation = Orientation.NORTH)
        }
    }

    fun rotateLeft() {
        val currentOrientation = this.mRobotState.value.orientation
        when (currentOrientation) {
            Orientation.NORTH -> this.mRobotState.value = RobotState(orientation = Orientation.WEST)
            Orientation.EAST -> this.mRobotState.value = RobotState(orientation = Orientation.NORTH)
            Orientation.SOUTH -> this.mRobotState.value = RobotState(orientation = Orientation.EAST)
            Orientation.WEST -> this.mRobotState.value = RobotState(orientation = Orientation.SOUTH)
        }
    }

    //TODO: Logic for moving forward
    //TODO: Logic to show error if there is a collision
}