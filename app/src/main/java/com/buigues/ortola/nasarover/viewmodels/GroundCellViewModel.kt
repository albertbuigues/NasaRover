package com.buigues.ortola.nasarover.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.buigues.ortola.nasarover.models.ground.GroundCellState

class GroundCellViewModel(): ViewModel() {

    private var mGroundCellState = mutableStateOf(GroundCellState())
    val groundCellState: State<GroundCellState> = mGroundCellState

    fun updateState(newState: GroundCellState) {
        mGroundCellState.value = newState
    }

    fun robotInside() {
        this.mGroundCellState.value = GroundCellState(isRobotInside = true)
    }

    fun robotOutside() {
        this.mGroundCellState.value = GroundCellState(isRobotInside = false)
    }
}