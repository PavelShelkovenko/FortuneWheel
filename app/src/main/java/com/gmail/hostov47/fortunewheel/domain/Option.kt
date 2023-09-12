package com.gmail.hostov47.fortunewheel.domain

import androidx.compose.ui.graphics.Color
import co.yml.charts.ui.piechart.models.PieChartData
import kotlin.random.Random

data class Option(
    val name: String,
    val value: Float,
    val color: Color
)

fun List<Option>.toSliceList(): List<PieChartData.Slice> {
    val newList: MutableList<PieChartData.Slice> = mutableListOf()
    this.forEach {
        newList.add(
            PieChartData.Slice(
                label = it.name,
                value = it.value,   //(100f / this.size.toFloat()),
                color = it.color
            )
        )

    }
    return newList.toList()
}

fun getRandomColor(): Color {
    return Color(
        Random.nextInt(0, 255),
        Random.nextInt(0, 255),
        Random.nextInt(0, 255)
    )
}