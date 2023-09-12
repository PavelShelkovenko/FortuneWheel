/**
 * Created by Pavel Shelkovenko on 25.07.2023.
 */

package com.gmail.hostov47.fortunewheel.presentation

import androidx.lifecycle.ViewModel
import com.gmail.hostov47.fortunewheel.components.dialog.DialogState
import com.gmail.hostov47.fortunewheel.domain.Option
import com.gmail.hostov47.fortunewheel.domain.getRandomColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FortuneWheelViewModel: ViewModel() {

    private val testOptionList = listOf<Option>(
        Option("test option",14f, getRandomColor()),
        Option("test option",17f, getRandomColor()),
        Option("test option",28f, getRandomColor()),
        Option("test option",32f, getRandomColor()),
        Option("test option",78f, getRandomColor()),
        Option("test option",23f, getRandomColor()),
        Option("test option",35f, getRandomColor()),
        Option("test option",10f, getRandomColor()),
        Option("test option",89f, getRandomColor()),
        Option("test option",15f, getRandomColor()),
        Option("test option",43f, getRandomColor()),
        Option("test option",19f, getRandomColor()),
        Option("test option",12f, getRandomColor()),
    )

    private val testOptionList2 = listOf<Option>(
        Option("1",14f, getRandomColor()),
        Option("2",14f, getRandomColor()),
        Option("3",14f, getRandomColor()),
        Option("4",14f, getRandomColor()),
        Option("5",14f, getRandomColor()),
        Option("6",14f, getRandomColor()),
        Option("7",14f, getRandomColor()),
        Option("8",14f, getRandomColor()),
        Option("9",14f, getRandomColor()),
    )

    var dialogState = MutableStateFlow<DialogState>(DialogState())
        private set

    var optionList = MutableStateFlow<List<Option>>(emptyList())
        private set

    fun onDialogStateChange(newValue: Boolean) {
        if(newValue != dialogState.value.isShown) {
            dialogState.update {
                it.copy(
                    isShown = newValue
                )
            }
        }
    }

    fun onDialogNameChange(newValue: String) {
        if (newValue != dialogState.value.dialogText) {
            dialogState.update {
                it.copy(
                    dialogText = newValue
                )
            }
        }
    }

    fun onDialogValueChange(newValue: String) {
        if (newValue != dialogState.value.dialogValue) {
            dialogState.update {
                it.copy(
                    dialogValue = newValue
                )
            }
        }
    }

    fun deleteOptionItem(option: Option) {
        val newList = optionList.value.toMutableList()
        newList.remove(option)
        optionList.value = newList
    }

    fun addOption(newOption: Option) {
        val newList: MutableList<Option> = optionList.value.toMutableList()
        newList.add(newOption)
        optionList.value = newList
        dialogState.update {
            it.copy(
                isShown = false,
                dialogText = "",
                dialogValue = ""
            )
        }
    }
}