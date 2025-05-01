package com.buigues.ortola.nasarover.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.buigues.ortola.nasarover.models.Coordinates
import com.buigues.ortola.nasarover.models.ground.GroundCellState

class GroundCellViewModel(coordinates: Coordinates): ViewModel() {

    private var mGroundCellState = mutableStateOf(GroundCellState())
    val groundCellState: State<GroundCellState> = mGroundCellState

    init {
        this.mGroundCellState.value = GroundCellState(associatedCoordinates = coordinates)
    }

    fun robotInside() {
        this.mGroundCellState.value = GroundCellState(isRobotInside = true)
    }

    fun robotOutside() {
        this.mGroundCellState.value = GroundCellState(isRobotInside = false)
    }
}