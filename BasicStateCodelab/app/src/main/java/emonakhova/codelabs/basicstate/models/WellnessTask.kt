package emonakhova.codelabs.basicstate.models


val wellnessTasks = List(30) { i -> WellnessTask(i, "Task # $i") }

data class WellnessTask(val id: Int, val label: String)