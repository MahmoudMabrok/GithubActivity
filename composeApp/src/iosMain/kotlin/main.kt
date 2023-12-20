import androidx.compose.ui.window.ComposeUIViewController
import tools.mo3ta.githubactivity.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
