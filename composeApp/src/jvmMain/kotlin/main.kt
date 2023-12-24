import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import tools.mo3ta.githubactivity.AppWithCommonTheme
import java.awt.Dimension

fun main() = application {
    Window(
        title = "GithubActivity",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(350, 600)
        AppWithCommonTheme()
    }
}