package com.buigues.ortola.nasarover.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.buigues.ortola.data.models.NasaInstructions

class AppViewModel(): ViewModel() {

    private var _instructionsState: MutableState<NasaInstructions?> = mutableStateOf(null)
    val instructionsState: State<NasaInstructions?> = _instructionsState

    fun updateState(newInstructions: NasaInstructions) {
        _instructionsState.value = newInstructions
    }
}