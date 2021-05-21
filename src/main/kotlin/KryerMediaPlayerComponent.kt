import androidx.compose.runtime.MutableState
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent

class KryerMediaPlayerComponent(private val position: MutableState<Float>) : EmbeddedMediaPlayerComponent() {

    override fun playing(mediaPlayer: MediaPlayer?) {
        super.playing(mediaPlayer)
        println("video playing")
    }

    override fun positionChanged(mediaPlayer: MediaPlayer?, newPosition: Float) {
        super.positionChanged(mediaPlayer, newPosition)
        position.value = newPosition
    }
}