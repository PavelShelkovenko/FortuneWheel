/**
 * Created by Pavel Shelkovenko on 26.07.2023.
 */
package com.gmail.hostov47.fortunewheel.components.option_list


import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gmail.hostov47.fortunewheel.components.dialog.AddItemDialog
import com.gmail.hostov47.fortunewheel.components.dialog.DialogState
import com.gmail.hostov47.fortunewheel.domain.Option
import com.gmail.hostov47.fortunewheel.domain.getRandomColor
import com.gmail.hostov47.fortunewheel.ui.theme.FortuneWheelTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OptionList(
    optionList: List<Option>,
    dialogState: DialogState,
    onWheelGenerate: () -> Unit,
    onDialogStateChange: (Boolean) -> Unit,
    onDialogTextChange: (String) -> Unit,
    onDialogValueChange: (String) -> Unit,
    addOption: (Option) -> Unit,
    onDeleteOptionItem: (Option) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar {
                Button(
                    onClick = { onWheelGenerate() },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 100.dp, end = 10.dp),
                    colors =  ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = MaterialTheme.colors.primary
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Generate Fortune Wheel")
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = { onDialogStateChange(true) },
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "icon")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true,
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            if (dialogState.isShown) {
                Dialog(
                    onDismissRequest = {
                        onDialogStateChange(false)
                    },
                    content = {
                        AddItemDialog(
                            dialogState.dialogText,
                            dialogState.dialogValue, {
                                onDialogTextChange(it)
                            }, {
                                onDialogValueChange(it)
                            }, {
                                addOption(it)
                            }
                        )
                    },
                    properties = DialogProperties(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    )
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(optionList) { option ->
                            Row(modifier = Modifier.animateItemPlacement(tween(durationMillis = 250))) {
                                OptionItem(option = option) {
                                    onDeleteOptionItem(it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
