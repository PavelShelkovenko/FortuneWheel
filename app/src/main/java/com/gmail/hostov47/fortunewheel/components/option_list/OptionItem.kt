/**
 * Created by Pavel Shelkovenko on 26.07.2023.
 */

package com.gmail.hostov47.fortunewheel.components.option_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.hostov47.fortunewheel.domain.Option
import com.gmail.hostov47.fortunewheel.ui.theme.FortuneWheelTheme


@Composable
fun OptionItem(option: Option, onDeleteOptionItem: (Option) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .shadow(elevation = 7.dp, shape = RoundedCornerShape(18.dp))
            .clip(RoundedCornerShape(32.dp))
            .background(option.color)
    ) {
        Text(
            text = option.name,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = option.value.toString(),
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(
            onClick = {
                onDeleteOptionItem(option)
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OptionItemPreview() {
    FortuneWheelTheme {
        OptionItem(Option("some option",10f ,Color.Cyan)) {}
    }
}
