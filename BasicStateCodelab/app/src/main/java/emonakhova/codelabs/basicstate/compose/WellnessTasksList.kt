package emonakhova.codelabs.basicstate.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import emonakhova.codelabs.basicstate.models.WellnessTask

@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    modifier: Modifier = Modifier,
    onCloseTask: (WellnessTask) -> Unit
) {
    LazyColumn {
        items(items = list, key = { task -> task.id }) { task ->
            WellnessTaskItem(
                taskName = task.label,
                modifier = modifier,
                onClose = { onCloseTask(task) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessTasksList() {
    WellnessTasksList(
        list = listOf(),
        modifier = Modifier,
        onCloseTask = {}
    )
}

