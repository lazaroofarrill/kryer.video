package views

import KryerMediaPlayerComponent
import androidx.compose.desktop.LocalAppWindow
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import controls.*
import java.awt.Dimension

@ExperimentalFoundationApi
@Composable
fun AppLayout(args: Array<String>) {
    val currentWindow = LocalAppWindow.current
    val setFullScreen = remember { mutableStateOf(false) }
    val showControlBar = remember { mutableStateOf(true) }
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
    val volume = remember { mutableStateOf(100) }

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
                    volume
                )
            )
        }

    mediaPlayerComponent.value.playToggleAction = { togglePlay(mediaPlayerComponent.value, playing) }
    mediaPlayerComponent.value.seekForwardAcion = { seekForward(mediaPlayerComponent.value, playing) }
    mediaPlayerComponent.value.seekBackWardAction = { seekBackward(mediaPlayerComponent.value, playing) }
    mediaPlayerComponent.value.volumeUpAction = { volumeUp(mediaPlayerComponent.value) }
    mediaPlayerComponent.value.volumeDownAction = { volumeDown(mediaPlayerComponent.value) }
    val readyToPlay = remember { mutableStateOf(false) }

    if (readyToPlay.value) {
        if (!mediaPlayerComponent.value.mediaPlayer().media().isValid) {
            mediaPlayerComponent.value.mediaPlayer().media().prepare(videoURl.value)
        }
    }

    MaterialTheme {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Row(modifier.weight(1f).fillMaxWidth().background(Color.Green)) {
                PlayerFrame(
                    modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    mediaPlayerComponent, readyToPlay
                )
                if (showPlaylist.value) {
                    Playlist(modifier)
                }
            }
            if (showControlBar.value && readyToPlay.value) {
                ControlBar(
                    modifier,
                    showPlaylist,
                    mediaPlayerComponent,
                    videoPosition,
                    volume = volume,
                    playing = playing,
                    openFileAction = { openFile(currentWindow, mediaPlayerComponent = mediaPlayerComponent.value) }
                )
            }
        }
    }
}
