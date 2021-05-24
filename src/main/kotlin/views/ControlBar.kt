package views

import KryerMediaPlayerComponent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    volume: MutableState<Int>
) {
    Surface {
        Column(
            modifier.fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            VideoPosition(
                modifier = modifier,
                mediaPlayerComponent = mediaPlayerComponent,
                videoPosition = videoPosition
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {


                ControlButtons(playing, mediaPlayerComponent)

                Spacer(modifier.weight(1f))
                VolumeControl(mediaPlayerComponent, modifier.width(200.dp), volume)

                IconButton(
                    onClick = { showPlaylist.value = !showPlaylist.value },
                    modifier = modifier.background(Color.Transparent)
                ) {
                    Icon(
                        TablerIcons.Playlist, contentDescription = "Toggle Playlist",
                    )
                }
            }
        }
    }
}

