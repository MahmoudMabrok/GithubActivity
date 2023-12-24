package tools.mo3ta.githubactivity.model

import kotlinx.serialization.Serializable


@Serializable
data class RepoDetails(
    val created_at: String?,
    val description: String?,
    val fork: Boolean?,
    val forks_count: Int?,
    val id: Int?,
    val language: String?,
    val name: String?,
    val open_issues_count: Int?,
    val pushed_at: String?,
    val size: Int?,
    val stargazers_count: Int?,
    val updated_at: String?,
    val url: String?,
    val watchers_count: Int?,
)
