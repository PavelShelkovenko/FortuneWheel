/**
 * Created by Pavel Shelkovenko on 20.07.2023.
 */

package com.gmail.hostov47.fortunewheel.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.hostov47.fortunewheel.components.option_list.OptionList
import com.gmail.hostov47.fortunewheel.components.wheel.Wheel


@Composable
fun RootScreen(viewModel: FortuneWheelViewModel) {

    val dialogState by viewModel.dialogState.collectAsState()
    val optionList by viewModel.optionList.collectAsState()

    val navController = rememberNavController()
    val startDestination = "option_list"

    NavHost(navController, startDestination) {
        composable("option_list") {
            OptionList(
                optionList = optionList,
                dialogState = dialogState, {
                    navController.navigate("wheel")
                }, {
                    viewModel.onDialogStateChange(it)
                }, {
                    viewModel.onDialogNameChange(it)
                }, {
                    viewModel.onDialogValueChange(it)
                }, {
                    viewModel.addOption(it)
                }, {
                    viewModel.deleteOptionItem(it)
                }
            )
        }
        composable("wheel") {
            Wheel(optionList = optionList)
        }
    }
}