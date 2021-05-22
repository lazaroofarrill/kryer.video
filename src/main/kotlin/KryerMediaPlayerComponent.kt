import androidx.compose.runtime.MutableState
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent

class KryerMediaPlayerComponent(
    private val position: MutableState<Float>,
    private val playing: MutableState<Boolean>
) : EmbeddedMediaPlayerComponent() {

    override fun playing(mediaPlayer: MediaPlayer?) {
        super.playing(mediaPlayer)
        playing.value = true
    }

    override fun paused(mediaPlayer: MediaPlayer?) {
        super.paused(mediaPlayer)
        playing.value = false
    }

    override fun stopped(mediaPlayer: MediaPlayer?) {
        super.stopped(mediaPlayer)
        paused(mediaPlayer)
    }

    override fun positionChanged(mediaPlayer: MediaPlayer?, newPosition: Float) {
        super.positionChanged(mediaPlayer, newPosition)
        position.value = newPosition
    }
}