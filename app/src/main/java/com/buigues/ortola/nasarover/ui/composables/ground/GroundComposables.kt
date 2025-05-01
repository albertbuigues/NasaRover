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
import com.buigues.ortola.nasarover.ui.composables.robot.StatefulRobot
import com.buigues.ortola.nasarover.viewmodels.GroundCellViewModel
import com.buigues.ortola.nasarover.viewmodels.GroundGridViewModel
import org.koin.compose.koinInject
import org.koin.java.KoinJavaComponent.inject

@Composable
fun StatelessGroundCell(coordinates: Coordinates, isRobotInside: Boolean) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .border(width = 1.dp, color = Color.LightGray)
            .size(40.dp)
    ) {
        if (isRobotInside) {
            StatefulRobot()
        } else {
            Text(
                fontSize = 6.sp,
                color = Color.White,
                text = "(${coordinates.x}, ${coordinates.y})"
            )
        }
    }
}

@Composable
fun StatefulGroundCell(viewModel: GroundCellViewModel) {
    val cellState: GroundCellState by remember { viewModel.groundCellState }
    StatelessGroundCell(cellState.associatedCoordinates, cellState.isRobotInside)
}

@Composable
fun StatelessGroundGrid(rows: Int, columns: Int) {
    val gridItems = List(rows * columns) { it }
    val robotPosition = Coordinates(0, 0)
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        reverseLayout = true
    ) {
        itemsIndexed(gridItems) { index, _ ->
            val viewmodel = koinInject<GroundCellViewModel>()
            val row = index / rows
            val col = index % columns
            viewmodel.updateState(
                GroundCellState(
                    associatedCoordinates = Coordinates(row, col),
                    isRobotInside = robotPosition.x == col && robotPosition.y == row
                )
            )
            StatefulGroundCell(viewmodel)
        }
    }
}

@Composable
fun StatefulGroundGrid(viewModel: GroundGridViewModel = koinInject<GroundGridViewModel>()) {
    val gridState: GroundGridState by remember { viewModel.groundGridState }
    val rows = gridState.rows
    val columns = gridState.columns
    StatelessGroundGrid(rows, columns)
}

@Preview
@Composable
private fun PreviewGroundGrid() {
    StatefulGroundGrid()
}

@Preview
@Composable
private fun PreviewGroundCell() {
    StatefulGroundCell(viewModel = GroundCellViewModel())
}