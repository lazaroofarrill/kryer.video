package views

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Playlist(modifier: Modifier) {
    Surface(
        color = Color.DarkGray,
        modifier = modifier
            .defaultMinSize(minWidth = 400.dp)
            .fillMaxHeight()
    ) {
        Text("Here goes the playlist")
    }
}