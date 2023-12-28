package tools.mo3ta.githubactivity.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun LabelWithIcon(
    modifier: Modifier = Modifier,
    label: String,
    iconSource: ImageVector
                 ) {
    Row(
        modifier = modifier.padding(horizontal = 4.dp)
       ) {
        Icon(
            iconSource,
            contentDescription = ""
            )
        Text(label)
    }
}