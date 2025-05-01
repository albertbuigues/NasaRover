package com.buigues.ortola.nasarover.ui.composables.ground

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buigues.ortola.nasarover.models.Coordinates
import com.buigues.ortola.nasarover.models.ground.GroundCellState
import com.buigues.ortola.nasarover.models.ground.GroundGridState
import com.buigues.ortola.nasarover.ui.composables.robot.StatelessRobot
import com.buigues.ortola.nasarover.viewmodels.GroundGridViewModel

@Composable
fun StatelessGroundCell(cellState: GroundCellState) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .border(width = 1.dp, color = Color.LightGray)
            .size(40.dp)
    ) {
        if (cellState.isRobotInside) {
            StatelessRobot()
        } else {
            Text(
                fontSize = 6.sp,
                color = Color.White,
                text = "(${cellState.associatedCoordinates.x}, ${cellState.associatedCoordinates.y})"
            )
        }
    }
}

@Composable
fun StatelessGroundGrid(groundState: GroundGridState) {
    val gridItems = List(groundState.rows * groundState.columns) { it }
    val robotPosition = Coordinates(0, 0)
    LazyVerticalGrid(
        columns = GridCells.Fixed(groundState.columns),
        reverseLayout = true
    ) {
        itemsIndexed(gridItems) { index, _ ->
            val row = index / groundState.rows
            val col = index % groundState.columns
            StatelessGroundCell(
                cellState = GroundCellState(
                    associatedCoordinates = Coordinates(col, row),
                    isRobotInside = robotPosition.x == col && robotPosition.y == row
                )
            )
        }
    }
}

@Composable
fun StatefullGroundGrid(viewModel: GroundGridViewModel) {
    val gridState: GroundGridState by remember { viewModel.groundGridState }
    StatelessGroundGrid(gridState)
}

@Preview
@Composable
private fun PreviewGroundGrid() {
    StatefullGroundGrid(GroundGridViewModel(8, 8))
}

@Preview
@Composable
private fun PreviewGroundCell() {
    StatelessGroundCell(GroundCellState())
}