package emonakhova.codelabs.basicstate

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import emonakhova.codelabs.basicstate.models.WellnessTask

class WellnessViewModel : ViewModel() {
    private val _tasks = List(30) { i ->
        WellnessTask(i, "Task # $i")
    }.toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun removeTask(task: WellnessTask) {
        _tasks.remove(task)
    }

}