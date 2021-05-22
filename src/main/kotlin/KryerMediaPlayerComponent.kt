import androidx.compose.desktop.AppWindow
import androidx.compose.runtime.MutableState
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent

class KryerMediaPlayerComponent(
    private val position: MutableState<Float>,
    private val playing: MutableState<Boolean>,
    private val setFullScreen: MutableState<Boolean>,
    private val parentWindow: AppWindow,
) : EmbeddedMediaPlayerComponent() {

    var lastX = 0
    var lastY = 0
    var playToggleAction = {}
    var seekForwardAcion = {}
    var seekBackWardAction = {}


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

    override fun mouseClicked(e: MouseEvent?) {
        super.mouseClicked(e)
        if (e?.clickCount == 2 && e.button == MouseEvent.BUTTON1) {
            println("toggling fullscreen")
            setFullScreen.value = !setFullScreen.value
        }
    }

    override fun mousePressed(e: MouseEvent?) {
        super.mousePressed(e)
        if (e == null) {
            return
        }

        lastX = e.x
        lastY = e.y
    }

    override fun mouseDragged(e: MouseEvent?) {
        super.mouseDragged(e)
        if (e == null) return

        val x = parentWindow.x
        val y = parentWindow.y
        parentWindow.setLocation(x - lastX + e.x, y - lastY + e.y)
    }

    override fun mouseMoved(e: MouseEvent?) {
        super.mouseMoved(e)
    }

    override fun keyPressed(e: KeyEvent?) {
        super.keyPressed(e)
        if (e == null) {
            return
        }
        println(e.keyCode)
        when (e.keyCode) {
            32 -> playToggleAction()
            39 -> seekForwardAcion()
            37 -> seekBackWardAction()
            38 -> {
                val vol = mediaPlayer().audio().volume()
                mediaPlayer().audio().setVolume(vol + 5)
            }
            40 -> {
                val vol = mediaPlayer().audio().volume()
                mediaPlayer().audio().setVolume(vol - 5)
            }
        }
    }
}