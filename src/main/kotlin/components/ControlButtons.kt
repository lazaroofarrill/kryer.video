package components

import KryerMediaPlayerComponent
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import controls.seekBackward
import controls.seekForward
import controls.togglePlay

@Composable
fun ControlButtons(
    modifier: Modifier,
    playing: MutableState<Boolean>,
    mediaPlayerComponent: MutableState<KryerMediaPlayerComponent>
) {
    val controlButtons = mapOf<ImageVector, () -> Unit>(
        Pair(TablerIcons.PlayerSkipBack, { seekBackward(mediaPlayerComponent.value, playing) }),
        Pair(
            if (!playing.value) TablerIcons.PlayerPlay else TablerIcons.PlayerPause,
            { togglePlay(mediaPlayerComponent = mediaPlayerComponent.value, playing = playing) }
        ),
        Pair(TablerIcons.PlayerStop, {
            mediaPlayerComponent.value.mediaPlayer().controls().stop()
        }),
        Pair(TablerIcons.PlayerSkipForward, { seekForward(mediaPlayerComponent.value, playing) })
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
}