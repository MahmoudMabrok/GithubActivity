package tools.mo3ta.githubactivity.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ForkRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tools.mo3ta.githubactivity.model.RepoDetails

@Composable
fun RepoItem(repoDetails: RepoDetails) {
    Card(
        modifier = Modifier.fillMaxWidth().padding
            (bottom = 16.dp, start = 16.dp, end = 16.dp)
        ) {
        Column(modifier = Modifier.padding(8.dp)) {
            repoDetails.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge
                    )
            }
            repoDetails.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall
                    )
            }
            Row {
                Text(
                    text = repoDetails.language ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .width(80.dp)
                        .align(Alignment.CenterVertically)
                    )
                repoDetails.stargazers_count?.let {
                    LabelWithIcon(
                        label = it.toString(),
                        iconSource = Icons.Default.Star
                                 )
                }
                repoDetails.forks_count?.let {
                    LabelWithIcon(
                        label = it.toString(),
                        iconSource = Icons.Default.ForkRight
                                 )
                }

            }

        }
    }


}