package views

import KryerMediaPlayerComponent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
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
import components.VideoPosition
import components.VolumeControl
import java.awt.event.MouseEvent

@ExperimentalFoundationApi
@Composable
fun ControlBar(
    modifier: Modifier,
    showPlaylist: MutableState<Boolean>,
    mediaPlayerComponent: MutableState<KryerMediaPlayerComponent>,
    videoPosition: MutableState<Float>,
    playing: MutableState<Boolean>,
    playToggleAction: () -> Unit = {},
    seekForwardAction: () -> Unit,
    seekBackWardAction: () -> Unit,
    openFileAction: () -> Unit,
    volume: MutableState<Int>
) {
    var lastEvent = remember { mutableStateOf<MouseEvent?>(null) }

    Column(
        modifier.fillMaxWidth()
            .background(color = Color.DarkGray)
            .border(BorderStroke(1.dp, Color.Transparent))
            .padding(horizontal = 10.dp)
    ) {
        VideoPosition(modifier = modifier, mediaPlayerComponent = mediaPlayerComponent, videoPosition = videoPosition)

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            val controlButtons = mapOf<ImageVector, () -> Unit>(
                Pair(Icons.Filled.KeyboardArrowLeft, seekBackWardAction),
                Pair(if (!playing.value) Icons.Filled.PlayArrow else Icons.Filled.Close, playToggleAction),
                Pair(Icons.Filled.Search, {
                    mediaPlayerComponent.value.mediaPlayer().controls().stop()
                }),
                Pair(Icons.Filled.KeyboardArrowRight, seekForwardAction)
            )

            OutlinedButton(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent, contentColor = Color.White
                ), onClick = openFileAction
            ) {
                Text("File")
            }

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
            VolumeControl(mediaPlayerComponent, modifier.weight(1f), volume)

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
}

