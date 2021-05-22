package views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun Playlist(modifier: Modifier) {
    Surface(
        color = Color.DarkGray,
        modifier = modifier
            .defaultMinSize(minWidth = 400.dp)
            .fillMaxHeight()
    ) {
        Column(modifier) {
            Text("here goes the playlist")
        }
    }
}