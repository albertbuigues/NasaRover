package com.buigues.ortola.nasarover

import com.buigues.ortola.nasarover.viewmodels.AppViewModel
import com.buigues.ortola.nasarover.viewmodels.GroundCellViewModel
import com.buigues.ortola.nasarover.viewmodels.GroundGridViewModel
import com.buigues.ortola.nasarover.viewmodels.RobotViewModel
import org.koin.dsl.module

val appModule = module {
    single { RobotViewModel() }
    single { GroundGridViewModel() }
    single { AppViewModel() }
    factory { GroundCellViewModel() }
}