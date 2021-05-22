package views

import KryerMediaPlayerComponent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import java.awt.event.MouseEvent

@ExperimentalFoundationApi
@Composable
fun ControlBar(
    modifier: Modifier,
    showPlaylist: MutableState<Boolean>,
    mediaPlayerComponent: MutableState<KryerMediaPlayerComponent>,
    url: MutableState<String>,
    videoPosition: MutableState<Float>,
    playing: MutableState<Boolean>,
    playToggleAction: () -> Unit = {},
) {
    var lastEvent = remember { mutableStateOf<MouseEvent?>(null) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .background(color = Color.DarkGray)
            .border(BorderStroke(1.dp, Color.Transparent))
    ) {
        val controlButtons = mapOf<ImageVector, () -> Unit>(
            Pair(Icons.Filled.KeyboardArrowLeft, {}),
            Pair(if (!playing.value) Icons.Filled.PlayArrow else Icons.Filled.Close, playToggleAction),
            Pair(Icons.Filled.Search, {
                mediaPlayerComponent.value.mediaPlayer().controls().stop()
            }),
            Pair(Icons.Filled.KeyboardArrowRight, {})
        )

        for (button in controlButtons) {
            OutlinedButton(
                modifier = modifier,
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
            enabled = mediaPlayerComponent.value.mediaPlayer().media().isValid
        )
        Text(
            "${mediaPlayerComponent.value.mediaPlayer().status().time().millisToTime()}/${
                mediaPlayerComponent.value.mediaPlayer().status().length().millisToTime()
            }", color = Color.White, modifier = modifier.combinedClickable(onClick = {}, onDoubleClick = {
                println("double clicked")
            })
        )
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

fun Long.millisToTime(): String {
    val hours = this / 3_600_000
    val minutes = this / 60_000
    val seconds = this % 60_000 / 1000

    var time = if (hours > 0) "${hours.toString().pad()}:" else ""
    time += "${minutes.toString().pad()}:"
    time += seconds.toString().pad()
    return time
}

fun String.pad(char: Char = '0', digits: Int = 2): String {
    var padding = this
    while (padding.length < digits) {
        padding = char + padding
    }
    return padding
}