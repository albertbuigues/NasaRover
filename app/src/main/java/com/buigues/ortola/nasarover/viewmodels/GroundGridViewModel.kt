package com.buigues.ortola.nasarover.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.buigues.ortola.nasarover.models.ground.GroundGridState

class GroundGridViewModel(rows: Int, columns: Int): ViewModel() {

    private var mGroundGridState = mutableStateOf(GroundGridState())
    val groundGridState: State<GroundGridState> = mGroundGridState

    init {
        this.mGroundGridState.value = GroundGridState(rows = rows, columns = columns)
    }
}