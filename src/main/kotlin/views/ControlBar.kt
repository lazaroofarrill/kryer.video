package views

import KryerMediaPlayerComponent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import components.ControlButtons
import components.VideoPosition
import components.VolumeControl
import compose.icons.TablerIcons
import compose.icons.tablericons.Playlist

@ExperimentalFoundationApi
@Composable
fun ControlBar(
    modifier: Modifier,
    showPlaylist: MutableState<Boolean>,
    mediaPlayerComponent: MutableState<KryerMediaPlayerComponent>,
    videoPosition: MutableState<Float>,
    playing: MutableState<Boolean>,
    openFileAction: () -> Unit,
    volume: MutableState<Int>
) {
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
            OutlinedButton(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent, contentColor = Color.White
                ), onClick = openFileAction
            ) {
                Text("File")
            }

            ControlButtons(modifier, playing, mediaPlayerComponent)


            Spacer(modifier.weight(1f))
            VolumeControl(mediaPlayerComponent, modifier.width(200.dp), volume)

            OutlinedButton(
                onClick = { showPlaylist.value = !showPlaylist.value },
                modifier = modifier.background(Color.Transparent),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.White
                ),
            ) {
                Image(
                    TablerIcons.Playlist, contentDescription = null,
                    modifier = modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }
}

