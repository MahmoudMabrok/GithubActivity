package tools.mo3ta.githubactivity.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MobileTheme(content: @Composable() () -> Unit) {
    AppTheme(
        content = content,
        appBar = { TopAppBar(title = { Text("Github Activity") }) })
}


@Composable
fun CommonTheme(content: @Composable() () -> Unit) {
    AppTheme(content)
}