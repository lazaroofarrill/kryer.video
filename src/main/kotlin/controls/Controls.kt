package controls

import KryerMediaPlayerComponent
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay


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