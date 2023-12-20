package tools.mo3ta.githubactivity.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDetails(
    val avatar_url: String?,
    val bio: String?,
    val blog: String?,
    val company: String?,
    val created_at: String?,
    val email: String?,
    val followers: Int?,
    val followers_url: String?,
    val following: Int?,
    val gravatar_id: String?,
    val id: Int?,
    val location: String?,
    val login: String?,
    val name: String?,
    val node_id: String?,
    val organizations_url: String?,
    val public_gists: Int?,
    val public_repos: Int?,
    val twitter_username: String?,
)