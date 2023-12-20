package tools.mo3ta.githubactivity

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import tools.mo3ta.githubactivity.screens.config.ConfigScreen
import tools.mo3ta.githubactivity.theme.CommonTheme

@Composable
internal fun AppWithCommonTheme() = CommonTheme {
    AppContent()
}

@Composable
internal fun AppContent() = Navigator(ConfigScreen)


internal expect fun openUrl(url: String?)