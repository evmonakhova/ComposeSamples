package emonakhova.codelabs.basicstate

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import emonakhova.codelabs.basicstate.models.WellnessTask
import emonakhova.codelabs.basicstate.models.wellnessTasks

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    tasks: List<WellnessTask> = rememberSaveable { wellnessTasks }
) {
    LazyColumn {
        items(tasks) { task ->
            WellnessTaskItem(taskName = task.label, modifier = modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessTasksList() {
    WellnessTasksList(tasks = wellnessTasks)
}

