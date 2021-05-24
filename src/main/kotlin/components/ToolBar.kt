package components

import KryerMediaPlayerComponent
import androidx.compose.desktop.LocalAppWindow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.icons.TablerIcons
import compose.icons.tablericons.Settings
import controls.openFile

@Composable
fun ToolBar(mediaPlayerComponent: MutableState<KryerMediaPlayerComponent>, modifier: Modifier = Modifier) {
    val parentWindow = LocalAppWindow.current
    TopAppBar(Modifier.padding(0.dp)) {
        ToolBarButton(onClick = {
            openFile(
                mediaPlayerComponent = mediaPlayerComponent.value,
                parentWindow = parentWindow
            )
        }) {
            Text("File")
        }
        Spacer(modifier = modifier.weight(1f))
        IconButton(onClick = {}) {
            Icon(TablerIcons.Settings, contentDescription = "Settings")
        }
    }
}