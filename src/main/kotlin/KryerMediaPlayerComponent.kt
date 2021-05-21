import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent

class KryerMediaPlayerComponent : EmbeddedMediaPlayerComponent() {
    override fun playing(mediaPlayer: MediaPlayer?) {
        super.playing(mediaPlayer)
        println("video playing")
    }
}