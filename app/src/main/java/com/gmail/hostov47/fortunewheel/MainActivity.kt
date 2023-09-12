package com.gmail.hostov47.fortunewheel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.gmail.hostov47.fortunewheel.presentation.FortuneWheelViewModel
import com.gmail.hostov47.fortunewheel.presentation.RootScreen
import com.gmail.hostov47.fortunewheel.ui.theme.FortuneWheelTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val viewModel by viewModels<FortuneWheelViewModel>()

        super.onCreate(savedInstanceState)
        setContent {
            FortuneWheelTheme {
                RootScreen(viewModel = viewModel)
            }
        }
    }
}

