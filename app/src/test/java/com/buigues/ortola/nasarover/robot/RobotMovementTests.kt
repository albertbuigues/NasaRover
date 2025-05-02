package com.buigues.ortola.nasarover.robot

import com.buigues.ortola.nasarover.models.Coordinates
import com.buigues.ortola.nasarover.models.robot.Orientation
import com.buigues.ortola.nasarover.models.robot.RobotState
import com.buigues.ortola.nasarover.viewmodels.RobotViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RobotMovementTests {

    lateinit var robotViewModel: RobotViewModel
    val gridRows = 3
    val gridCols = 3

    @Before
    fun setup() {
        robotViewModel = RobotViewModel()
    }

    @Test
    fun `when orientation west and in first column cannot move else yes`() {
        robotViewModel.updateState(
            RobotState(orientation = Orientation.WEST)
        )
        Assert.assertFalse(robotViewModel.canMoveForward(gridRows, gridCols))
        robotViewModel.updateState(RobotState(coordinates = Coordinates(1, 0)))
        Assert.assertTrue(robotViewModel.canMoveForward(gridRows, gridCols))
    }

    @Test
    fun `when orientation south and in first row cannot move else yes`() {
        robotViewModel.updateState(
            RobotState(orientation = Orientation.SOUTH)
        )
        Assert.assertFalse(robotViewModel.canMoveForward(gridRows, gridCols))
        robotViewModel.updateState(RobotState(coordinates = Coordinates(0, 1)))
        Assert.assertTrue(robotViewModel.canMoveForward(gridRows, gridCols))
    }

    @Test
    fun `when orientation east and in last column cannot move else yes`() {
        robotViewModel.updateState(
            RobotState(orientation = Orientation.EAST, coordinates = Coordinates(2, 0))
        )
        Assert.assertFalse(robotViewModel.canMoveForward(gridRows, gridCols))
        robotViewModel.updateState(RobotState(coordinates = Coordinates(1, 0)))
        Assert.assertTrue(robotViewModel.canMoveForward(gridRows, gridCols))
    }

    @Test
    fun `when orientation north and in last row cannot move else yes`() {
        robotViewModel.updateState(
            RobotState(orientation = Orientation.NORTH, coordinates = Coordinates(0, 2))
        )
        Assert.assertFalse(robotViewModel.canMoveForward(gridRows, gridCols))
        robotViewModel.updateState(RobotState(coordinates = Coordinates(0, 1)))
        Assert.assertTrue(robotViewModel.canMoveForward(gridRows, gridCols))
    }

    @Test
    fun `when orientation north and move then coordinates are correct`() {
        robotViewModel.moveForward()
        Assert.assertEquals(robotViewModel.robotState.value.coordinates.y, 1)
        Assert.assertNotEquals(robotViewModel.robotState.value.coordinates.y, 0)
    }

    @Test
    fun `when orientation east and move then coordinates are correct`() {
        robotViewModel.updateState(RobotState(orientation = Orientation.EAST, coordinates = Coordinates(0, 0)))
        robotViewModel.moveForward()
        Assert.assertEquals(robotViewModel.robotState.value.coordinates.x, 1)
        Assert.assertNotEquals(robotViewModel.robotState.value.coordinates.x, 0)
    }

    @Test
    fun `when orientation west and move then coordinates are correct`() {
        robotViewModel.updateState(RobotState(orientation = Orientation.WEST, coordinates = Coordinates(1, 0)))
        robotViewModel.moveForward()
        Assert.assertEquals(robotViewModel.robotState.value.coordinates.x, 0)
        Assert.assertNotEquals(robotViewModel.robotState.value.coordinates.x, 1)
    }

    @Test
    fun `when orientation south and move then coordinates are correct`() {
        robotViewModel.updateState(RobotState(orientation = Orientation.SOUTH, coordinates = Coordinates(0, 1)))
        robotViewModel.moveForward()
        Assert.assertEquals(robotViewModel.robotState.value.coordinates.y, 0)
        Assert.assertNotEquals(robotViewModel.robotState.value.coordinates.y, 1)
    }
}