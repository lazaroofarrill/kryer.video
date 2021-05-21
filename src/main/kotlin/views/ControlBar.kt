package views

import KryerMediaPlayerComponent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ControlBar(
    modifier: Modifier,
    showPlaylist: MutableState<Boolean>,
    mediaPlayerComponent: MutableState<KryerMediaPlayerComponent>,
    url: MutableState<String>,
    videoPosition: MutableState<Float>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .background(color = Color.DarkGray)
            .border(BorderStroke(1.dp, Color.Transparent))
    ) {
        val controlButtons = mapOf<ImageVector, () -> Unit>(
            Pair(Icons.Filled.KeyboardArrowLeft, { }),
            Pair(Icons.Filled.PlayArrow, {
                mediaPlayerComponent.value.mediaPlayer().media().play(url.value)
                mediaPlayerComponent.value.mediaPlayer().fullScreen().set(true)
            }),
            Pair(Icons.Filled.KeyboardArrowRight, { })
        )

        for (button in controlButtons) {
            OutlinedButton(
                onClick = button.value,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.White
                ),
            ) {
                Icon(button.key, contentDescription = null)
            }
        }
        Spacer(modifier.padding(8.dp))

        Slider(
            value = videoPosition.value,
            onValueChange = {
                videoPosition.value = it
                mediaPlayerComponent.value.mediaPlayer().controls().setPosition(it)
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.White, activeTrackColor = Color.White, inactiveTrackColor = Color.Gray
            ),
            modifier = modifier.weight(1f),
        )
        Text("1:00/${mediaPlayerComponent.value.mediaPlayer().status().length().millisToTIme()}", color = Color.White)
        OutlinedButton(
            onClick = { showPlaylist.value = !showPlaylist.value },
            modifier = modifier.background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            ),
        ) {
            Image(
                Icons.Filled.List, contentDescription = null,
                modifier = modifier.size(30.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

fun Long.millisToTIme(): String {
    val minutes = this / 60000
    val seconds = this % 60000 / 1000
    return "${minutes}:${seconds}"
}