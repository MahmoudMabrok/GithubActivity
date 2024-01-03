package tools.mo3ta.githubactivity.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import tools.mo3ta.githubactivity.model.RepoDetails

@Composable
fun RepoList(repos: List<RepoDetails>) {
    Column {
        repos.map {
            RepoItem(it)
        }
    }
}