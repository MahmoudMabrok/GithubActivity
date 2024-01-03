package tools.mo3ta.githubactivity.model

import kotlinx.serialization.Serializable


@Serializable
data class RepoDetails(
    val created_at: String? = null,
    val description: String? = null,
    val fork: Boolean? = null,
    val forks_count: Int? = null,
    val id: Int? = null,
    val language: String? = null,
    val name: String? = null,
    val open_issues_count: Int? = null,
    val pushed_at: String? = null,
    val size: Int? = null,
    val stargazers_count: Int? = null,
    val updated_at: String? = null,
    val url: String? = null,
    val watchers_count: Int? = null,
                      )
