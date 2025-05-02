package com.buigues.ortola.nasarover.robot

import com.buigues.ortola.nasarover.models.robot.Orientation
import com.buigues.ortola.nasarover.models.robot.RobotState
import com.buigues.ortola.nasarover.viewmodels.RobotViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RobotRotationTests {

    lateinit var robotViewModel: RobotViewModel

    @Before
    fun setup() {
        robotViewModel = RobotViewModel()
    }

    @Test
    fun `when current orientation north and rotate right then orientation is east`() {
        robotViewModel.rotateRight()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.EAST)
    }

    @Test
    fun `when current orientation east and rotate right then orientation is south`() {
        robotViewModel.updateState(RobotState(orientation = Orientation.EAST))
        robotViewModel.rotateRight()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.SOUTH)
    }

    @Test
    fun `when current orientation south and rotate right then orientation is west`() {
        robotViewModel.updateState(RobotState(orientation = Orientation.SOUTH))
        robotViewModel.rotateRight()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.WEST)
    }

    @Test
    fun `when current orientation west and rotate right then orientation is north`() {
        robotViewModel.updateState(RobotState(orientation = Orientation.WEST))
        robotViewModel.rotateRight()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.NORTH)
    }

    @Test
    fun `when current orientation north and rotate left then orientation is west`() {
        robotViewModel.rotateLeft()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.WEST)
    }

    @Test
    fun `when current orientation west and rotate left then orientation is south`() {
        robotViewModel.updateState(RobotState(orientation = Orientation.WEST))
        robotViewModel.rotateLeft()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.SOUTH)
    }

    @Test
    fun `when current orientation south and rotate left then orientation is east`() {
        robotViewModel.updateState(RobotState(orientation = Orientation.SOUTH))
        robotViewModel.rotateLeft()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.EAST)
    }

    @Test
    fun `when current orientation east and rotate left then orientation is north`() {
        robotViewModel.updateState(RobotState(orientation = Orientation.EAST))
        robotViewModel.rotateLeft()
        Assert.assertEquals(robotViewModel.robotState.value.orientation, Orientation.NORTH)
    }
}