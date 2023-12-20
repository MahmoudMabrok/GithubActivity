import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import tools.mo3ta.githubactivity.AppContent
import tools.mo3ta.githubactivity.theme.MobileTheme

fun MainViewController(): UIViewController = ComposeUIViewController {    MobileTheme{ AppContent() } }
