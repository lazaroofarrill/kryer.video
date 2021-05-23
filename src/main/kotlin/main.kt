import androidx.compose.desktop.Window
import androidx.compose.foundation.ExperimentalFoundationApi
import views.AppLayout
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

@ExperimentalFoundationApi
fun main(args: Array<String>) =
    Window(icon = getIcon()) {

        AppLayout(args)
    }

fun getIcon(): BufferedImage {
    var image: BufferedImage? = null

    try {
        image = ImageIO.read(File("src/main/resources/sample.png"))
    } catch (e: Exception) {
        println("couldn't open image")
    }

    if (image == null) {
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)
    }

    return image
}