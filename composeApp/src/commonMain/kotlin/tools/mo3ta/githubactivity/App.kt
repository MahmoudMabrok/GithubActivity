package tools.mo3ta.githubactivity

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import tools.mo3ta.githubactivity.screens.config.ConfigScreen
import tools.mo3ta.githubactivity.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    Navigator(ConfigScreen)
}


internal expect fun openUrl(url: String?)