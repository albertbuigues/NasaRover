package com.buigues.ortola.nasarover.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.buigues.ortola.nasarover.models.robot.RobotState

class RobotViewModel: ViewModel() {

    private var mRobotState = mutableStateOf(RobotState())
    val robotState: State<RobotState> = mRobotState

    fun updateState(newState: RobotState) {
        mRobotState.value = newState
    }
}