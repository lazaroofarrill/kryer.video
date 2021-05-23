package components

import KryerMediaPlayerComponent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import controls.MaxVolume
import controls.volumeDown
import controls.volumeUp

@Composable
fun VolumeControl(
    mediaPlayerComponent: MutableState<KryerMediaPlayerComponent>,
    modifier: Modifier,
    volume: MutableState<Int>
) {
    Row(modifier) {
        IconButton(onClick = { volumeDown(mediaPlayerComponent.value) }) {
            Icon(Icons.Filled.KeyboardArrowDown, contentDescription = null)
        }
        Slider(
            value = volume.value.toFloat(),
            onValueChange = {
                volume.value = it.toInt()
                mediaPlayerComponent.value.mediaPlayer().audio().setVolume(it.toInt())
            },
            valueRange = 0f..MaxVolume.toFloat(),
            modifier = modifier,
            colors = SliderDefaults.colors(
                thumbColor = Color.White, activeTrackColor = Color.White, inactiveTrackColor = Color.Gray
            )
        )
        IconButton(onClick = { volumeUp(mediaPlayerComponent.value) }) {
            Icon(Icons.Filled.KeyboardArrowUp, contentDescription = null)
        }
    }
}