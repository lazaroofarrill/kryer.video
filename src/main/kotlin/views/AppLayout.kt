package views

import KryerMediaPlayerComponent
import androidx.compose.desktop.LocalAppWindow
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.unit.dp
import controls.seekBackward
import controls.seekForward
import controls.togglePlay
import java.awt.Dimension

@ExperimentalFoundationApi
@Composable
fun AppLayout(args: Array<String>) {
    val currentWindow = LocalAppWindow.current
    val setFullScreen = remember { mutableStateOf(false) }
    val showControlBar = remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    if (setFullScreen.value) {
        currentWindow.makeFullscreen()
        showControlBar.value = false
    } else {
        currentWindow.restore()
        showControlBar.value = true
    }

    currentWindow.keyboard.setShortcut(Key.Spacebar) {
        println("space bar pressed")
    }

    currentWindow.window.minimumSize = Dimension(600, 480)

    var videoFile =
        "/media/lazaroofarrill/Data1/Videos/series/bungo stray dogs/Bungou Stray Dogs Dead Apple (Pelicula)/Bungou Stray Dogs Dead Apple.mp4"
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
        remember {
            mutableStateOf(
                KryerMediaPlayerComponent(
                    videoPosition,
                    playing,
                    setFullScreen,
                    currentWindow,
                    videoURl
                )
            )
        }
    mediaPlayerComponent.value.playToggleAction = { togglePlay(mediaPlayerComponent.value, playing, videoURl) }
    mediaPlayerComponent.value.seekForwardAcion = { seekForward(mediaPlayerComponent.value, playing) }
    mediaPlayerComponent.value.seekBackWardAction = { seekBackward(mediaPlayerComponent.value, playing) }
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
            if (readyToPlay.value && showControlBar.value) {
                ControlBar(
                    modifier,
                    showPlaylist,
                    mediaPlayerComponent,
                    url = videoURl,
                    videoPosition,
                    playing = playing,
                    playToggleAction = {
                        togglePlay(
                            mediaPlayerComponent = mediaPlayerComponent.value,
                            playing,
                            videoURl
                        )
                    },
                    seekForwardAction = {
                        seekForward(mediaPlayerComponent = mediaPlayerComponent.value, playing)
                    },
                    seekBackWardAction = {
                        seekBackward(mediaPlayerComponent = mediaPlayerComponent.value, playing)
                    }
                )
            }
        }
    }
}
