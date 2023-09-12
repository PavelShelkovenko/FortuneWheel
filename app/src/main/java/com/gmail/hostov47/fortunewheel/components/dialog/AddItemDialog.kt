/**
 * Created by Pavel Shelkovenko on 25.07.2023.
 */

package com.gmail.hostov47.fortunewheel.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.hostov47.fortunewheel.domain.Option
import com.gmail.hostov47.fortunewheel.domain.getRandomColor
import com.gmail.hostov47.fortunewheel.ui.theme.FortuneWheelTheme


@Composable
fun AddItemDialog(
    dialogText: String,
    dialogValue: String,
    onDialogTextChange: (String) -> Unit,
    onDialogValueChange: (String) -> Unit,
    addOptionItem: (Option) -> Unit
) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(300.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Text("Add a new option", fontSize = 24.sp)
            Spacer(modifier = Modifier.weight(0.1f))
            TextField(
                value = dialogText,
                onValueChange = {
                    onDialogTextChange(it)
                },
                label = {
                    Text("Name of the option")
                }
            )
            Spacer(modifier = Modifier.weight(0.05f))
            TextField(
                value = dialogValue,
                onValueChange = {
                    onDialogValueChange(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = {
                    Text("Value of the option")
                }
            )
            Spacer(modifier = Modifier.weight(0.05f))
            Button(
                onClick = {
                    if (dialogText.isNotEmpty()) {
                        try {
                            addOptionItem(Option(dialogText, dialogValue.toFloat(), getRandomColor()))
                        } catch (e: Exception) {
                            println("Error while adding new option: $e")
                        }
                    }
                },
                modifier = Modifier.align(End)
            ) {
                Text("Add")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddItemDialogPreview() {
    FortuneWheelTheme {
        AddItemDialog("String", "String",{}, {}, {})
    }
}
