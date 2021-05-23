package controls

import KryerMediaPlayerComponent
import androidx.compose.desktop.AppWindow
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay

const val MillisToSecond = 1000
const val ShortSeekInterval = 5 * MillisToSecond
const val MaxVolume = 100


suspend fun hideControlBar(toggle: MutableState<Boolean>) {
    delay(500)
    toggle.value = false
}

suspend fun showControlBar(toggle: MutableState<Boolean>) {
    toggle.value = true
    delay(5000)
    hideControlBar(toggle)
}

fun togglePlay(
    mediaPlayerComponent: KryerMediaPlayerComponent,
    playing: MutableState<Boolean>,
    videoUrl: MutableState<String>
) {
    if (playing.value) {
        mediaPlayerComponent.mediaPlayer().controls().pause()
    } else {
        if (!mediaPlayerComponent.mediaPlayer().media().isValid) {
            mediaPlayerComponent.mediaPlayer().media().play(videoUrl.value)
        } else {
            mediaPlayerComponent.mediaPlayer().controls().play()
        }
    }
}

fun seekForward(mediaPlayerComponent: KryerMediaPlayerComponent, playing: MutableState<Boolean>) {
    if (playing.value) {
        val currentTime = mediaPlayerComponent.mediaPlayer().status().time()
        mediaPlayerComponent.mediaPlayer().controls().setTime(currentTime + ShortSeekInterval)
    }
}

fun seekBackward(mediaPlayerComponent: KryerMediaPlayerComponent, playing: MutableState<Boolean>) {
    if (playing.value) {
        val currentTime = mediaPlayerComponent.mediaPlayer().status().time()
        mediaPlayerComponent.mediaPlayer().controls().setTime(currentTime - ShortSeekInterval)
    }
}

fun openFile(parentWindow: AppWindow, mediaPlayerComponent: KryerMediaPlayerComponent) {
    val fd = java.awt.FileDialog(parentWindow.window)
    fd.isVisible = true
    val files = fd.files
    if (files.isNotEmpty()) {
        val mediaReady = mediaPlayerComponent.mediaPlayer().media().prepare(files[0].absolutePath)
        if (mediaReady) {
            mediaPlayerComponent.mediaPlayer().controls().play()
        } else {
            println("invalid file")
        }
    }
}

fun Long.millisToTime(): String {
    val hours = this / 3_600_000
    val minutes = this / 60_000 % 60
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

fun volumeUp(mediaPlayerComponent: KryerMediaPlayerComponent) {
    val vol = mediaPlayerComponent.mediaPlayer().audio().volume()
    if (vol > MaxVolume) {
        mediaPlayerComponent.mediaPlayer().audio().setVolume(MaxVolume)
        return
    }
    mediaPlayerComponent.mediaPlayer().audio().setVolume(vol + 5)
}

fun volumeDown(mediaPlayerComponent: KryerMediaPlayerComponent) {
    val vol = mediaPlayerComponent.mediaPlayer().audio().volume()
    mediaPlayerComponent.mediaPlayer().audio().setVolume(vol - 5)
}

fun toggleFullScreen(setFullScreen: MutableState<Boolean>) {
    setFullScreen.value = !setFullScreen.value
}