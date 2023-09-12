/**
 * Created by Pavel Shelkovenko on 26.07.2023.
 */

package com.gmail.hostov47.fortunewheel.components.wheel

import android.util.Log
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.gmail.hostov47.fortunewheel.domain.Option
import com.gmail.hostov47.fortunewheel.domain.getRandomColor
import com.gmail.hostov47.fortunewheel.domain.toSliceList
import com.gmail.hostov47.fortunewheel.ui.theme.FortuneWheelTheme
import kotlin.random.Random


@Composable
fun Wheel(optionList: List<Option>) {

    val pieChartData = PieChartData(
        slices = optionList.toSliceList(),
        plotType = PlotType.Pie
    )

    val pieChartConfig = PieChartConfig(
        isAnimationEnable = true,
        showSliceLabels = true,
        animationDuration = 1500
    )

    var rotationValue by remember {
        mutableStateOf(0f)
    }

    var optionName by remember {
        mutableStateOf("")
    }

    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = Random.nextInt(1500, 6500),
            easing = CubicBezierEasing(0.0f, 0.0f, 0.01f, 1.0f)
        ),
        label = "",
        finishedListener = {
            val index = (365f - (rotationValue % 360f)) / (360f / optionList.size)
            Log.d("MyLog", "index: ${index}")
        }
    )

    if (optionList.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PieChart(
                modifier = Modifier
                    .width(400.dp)
                    .height(400.dp)
                    .background(Color.White)
                    .rotate(angle),
                pieChartData,
                pieChartConfig
            )

            Button(
                modifier = Modifier
                    .width(130.dp)
                    .padding(start = 10.dp, end = 10.dp),
                onClick = {
                    rotationValue = ((0..360).random().toFloat() + 720) + angle
                }
            ) {
                Text(
                    text = "Start",
                    color = Color.White
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(30.dp)
                    .graphicsLayer {
                        this.translationY = -180.dp.toPx()
                    },
                onDraw = {
                    val path = Path()
                    val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
                    path.moveTo(0f, 0f)
                    path.lineTo(size.width / 2f, size.height / 2f)
                    path.lineTo(size.width, 0f)
                    path.close()
                    drawPath(path = path, brush = brush)
                }
            )
        }
    } else {
        Text("No option added")
    }
}

@Preview(showBackground = true)
@Composable
fun WheelPreview() {
    FortuneWheelTheme {
        Wheel(
            listOf<Option>(
                Option("test option", 10f, getRandomColor()),
                Option("test option", 10f, getRandomColor()),
                Option("test option", 10f, getRandomColor()),
                Option("test option", 10f, getRandomColor()),
                Option("test option", 10f, getRandomColor()),
                Option("test option", 10f, getRandomColor()),
                Option("test option", 10f, getRandomColor()),
                Option("test option", 10f, getRandomColor()),
                Option("test option", 10f, getRandomColor()),
                Option("test option", 10f, getRandomColor()),
            )
        )
    }
}