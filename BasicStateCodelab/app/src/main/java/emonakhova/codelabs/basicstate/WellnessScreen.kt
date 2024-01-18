package emonakhova.codelabs.basicstate

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
import emonakhova.codelabs.basicstate.ui.theme.BasicStateCodelabTheme

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    StatefulCounter(modifier)
}

@Composable
private fun StatefulCounter(modifier: Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    var showTask by rememberSaveable { mutableStateOf(true) }
    StatelessCounter(
        count = count,
        showTask = showTask,
        onCloseWellnessTaskClicked = { showTask = false },
        onAddGlassOfWaterClicked = {
            showTask = true
            count++
        },
        onClearWaterCounterClicked = { count = 0 },
        modifier = modifier
    )
}

@Composable
fun StatelessCounter(
    count: Int,
    showTask: Boolean,
    onCloseWellnessTaskClicked: () -> Unit,
    onAddGlassOfWaterClicked: () -> Unit,
    onClearWaterCounterClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            if (showTask) {
                WellnessTaskItem(
                    onClose = onCloseWellnessTaskClicked,
                    taskName = "Have you taken your 15 minute walk today?"
                )
            }
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