package views

import androidx.compose.desktop.SwingPanel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent

@Composable
fun PlayerFrame(
    modifier: Modifier,
//    mediaPlayerComponent: EmbeddedMediaPlayerComponent, //passing a regular variable loses video on rerender
    mediaPlayerComponent: MutableState<EmbeddedMediaPlayerComponent>, //this works just fine
    readyToPlay: MutableState<Boolean>
) {
    NativeDiscovery().discover()
    SideEffect {
        readyToPlay.value = true
    }

    return SwingPanel(
        background = Color.Green,
        modifier = modifier.fillMaxSize()
            .padding(0.dp), //I have no idea why I need to set padding for the component to autoresize
        factory = {
            mediaPlayerComponent.value
        }
    )
}


