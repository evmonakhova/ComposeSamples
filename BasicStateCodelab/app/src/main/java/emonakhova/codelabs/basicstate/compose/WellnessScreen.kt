package emonakhova.codelabs.basicstate.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import emonakhova.codelabs.basicstate.WellnessViewModel
import emonakhova.codelabs.basicstate.ui.theme.BasicStateCodelabTheme

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    viewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StatefulCounter()
        WellnessTasksList(
            list = viewModel.tasks,
            onCloseTask = { task -> viewModel.removeTask(task) },
            onCheckedChange = { task -> viewModel.onCheckedChange(task) }
        )
    }
}

@Composable
private fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(
        count = count,
        onAddGlassOfWaterClicked = { count++ },
        onClearWaterCounterClicked = { count = 0 },
        modifier = modifier
    )
}

@Composable
fun StatelessCounter(
    count: Int,
    onAddGlassOfWaterClicked: () -> Unit,
    onClearWaterCounterClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text(
                text = "You've had $count glasses.",
                modifier = modifier.padding(16.dp)
            )
        }
        Row(Modifier.padding(top = 8.dp)) {
            Button(
                onClick = onAddGlassOfWaterClicked,
                enabled = count < 10
            ) {
                Text("Add one")
            }
            Button(
                onClick = onClearWaterCounterClicked,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Clear water counter")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessScreenPreview() {
    BasicStateCodelabTheme {
        WellnessScreen()
    }
}