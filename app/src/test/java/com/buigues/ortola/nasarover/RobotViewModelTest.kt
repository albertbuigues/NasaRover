package com.buigues.ortola.nasarover

import com.buigues.ortola.nasarover.models.robot.Orientation
import com.buigues.ortola.nasarover.models.robot.RobotState
import com.buigues.ortola.nasarover.viewmodels.RobotViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RobotViewModelTest {

    @Test
    fun `when current orientation north and rotate right then orientation is east`() {
        val robotViewModel = RobotViewModel()
        robotViewModel.rotateRight()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.EAST)
    }

    @Test
    fun `when current orientation east and rotate right then orientation is south`() {
        val robotViewModel = RobotViewModel()
        robotViewModel.updateState(RobotState(orientation = Orientation.EAST))
        robotViewModel.rotateRight()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.SOUTH)
    }

    @Test
    fun `when current orientation south and rotate right then orientation is west`() {
        val robotViewModel = RobotViewModel()
        robotViewModel.updateState(RobotState(orientation = Orientation.SOUTH))
        robotViewModel.rotateRight()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.WEST)
    }

    @Test
    fun `when current orientation west and rotate right then orientation is north`() {
        val robotViewModel = RobotViewModel()
        robotViewModel.updateState(RobotState(orientation = Orientation.WEST))
        robotViewModel.rotateRight()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.NORTH)
    }

    @Test
    fun `when current orientation north and rotate left then orientation is west`() {
        val robotViewModel = RobotViewModel()
        robotViewModel.rotateLeft()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.WEST)
    }

    @Test
    fun `when current orientation west and rotate left then orientation is south`() {
        val robotViewModel = RobotViewModel()
        robotViewModel.updateState(RobotState(orientation = Orientation.WEST))
        robotViewModel.rotateLeft()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.SOUTH)
    }

    @Test
    fun `when current orientation south and rotate left then orientation is east`() {
        val robotViewModel = RobotViewModel()
        robotViewModel.updateState(RobotState(orientation = Orientation.SOUTH))
        robotViewModel.rotateLeft()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.EAST)
    }

    @Test
    fun `when current orientation east and rotate left then orientation is north`() {
        val robotViewModel = RobotViewModel()
        robotViewModel.updateState(RobotState(orientation = Orientation.EAST))
        robotViewModel.rotateLeft()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.NORTH)
    }
}