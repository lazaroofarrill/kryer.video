import androidx.compose.desktop.LocalAppWindow
import androidx.compose.desktop.Window
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import views.ControlBar
import views.PlayerFrame
import views.Playlist
import java.awt.Dimension

@ExperimentalFoundationApi
fun main(args: Array<String>) = Window {

    val currentWindow = LocalAppWindow.current
    val setFullScreen = remember { mutableStateOf(false) }
    if (setFullScreen.value) {
        currentWindow.makeFullscreen()
    } else {
        currentWindow.restore()
    }

    currentWindow.window.minimumSize = Dimension(600, 480)

    var videoFile = "/home/lazaroofarrill/Videos/Big_Buck_Bunny_first_23_seconds_1080p.ogv"
    if (args.isNotEmpty()) {
        videoFile = args[0]
    }
    val modifier = Modifier
    modifier.background(Color.Black)

    val videoURl =
        remember { mutableStateOf(videoFile) }
    val videoPosition = remember { mutableStateOf(0f) }

    val playing = remember { mutableStateOf(false) }

    val showPlaylist: MutableState<Boolean> = remember { mutableStateOf(false) }
    val mediaPlayerComponent =
        remember { mutableStateOf(KryerMediaPlayerComponent(videoPosition, playing, setFullScreen, currentWindow)) }
    val readyToPlay = remember { mutableStateOf(false) }

    MaterialTheme {
        Column(
            modifier = modifier.fillMaxSize().defaultMinSize(600.dp, 480.dp)
        ) {
            Row(modifier.weight(1f).fillMaxWidth()) {
                PlayerFrame(
                    modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    mediaPlayerComponent, readyToPlay, setFullScreen
                )
                if (showPlaylist.value) {
                    Playlist(modifier)
                }
            }
            if (readyToPlay.value) {
                ControlBar(
                    modifier,
                    showPlaylist,
                    mediaPlayerComponent,
                    url = videoURl,
                    videoPosition,
                    playing = playing
                )
            }
        }
    }
}
