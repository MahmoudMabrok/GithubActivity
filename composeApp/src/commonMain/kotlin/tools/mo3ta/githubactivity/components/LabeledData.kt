package tools.mo3ta.githubactivity.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LabeledData(title: String, value: String, modifier: Modifier = Modifier) {
    Card(modifier) {
        Column(
            modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp)
              ) {
            Text(
                value,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                )
        }
    }
}