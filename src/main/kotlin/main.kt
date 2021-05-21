import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import views.ControlBar
import views.PlayerFrame
import views.Playlist


fun main() = Window {
    val modifier = Modifier
    modifier.background(Color.Black)
    val videoURl =
        remember { mutableStateOf("/home/lazaroofarrill/Videos/El crash del 83 _ La Leyenda del Videojuego [Episodio 4]-G39At1Ojx-E.webm") }
    val videoPosition = remember { mutableStateOf(0f) }


    val showPlaylist: MutableState<Boolean> = remember { mutableStateOf(false) }
    val mediaPlayerComponent = remember { mutableStateOf(KryerMediaPlayerComponent(videoPosition)) }
    val readyToPlay = remember { mutableStateOf(false) }

    MaterialTheme {
        Column(modifier = modifier.fillMaxHeight().fillMaxWidth()) {
            Row(modifier.weight(1f).fillMaxWidth()) {
                PlayerFrame(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    mediaPlayerComponent = mediaPlayerComponent, readyToPlay = readyToPlay
                )
                if (showPlaylist.value) {
                    Playlist(modifier)
                }
            }
            if (readyToPlay.value) {
                ControlBar(modifier, showPlaylist, mediaPlayerComponent, url = videoURl, videoPosition)
            }
        }
    }
}