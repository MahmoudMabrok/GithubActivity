package tools.mo3ta.githubactivity.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDetails(
    val avatar_url: String? = null,
    val bio: String? = null,
    val blog: String? = null,
    val company: String? = null,
    val created_at: String? = null,
    val email: String? = null,
    val followers: Int? = 0,
    val followers_url: String? = null,
    val following: Int? = 0,
    val gravatar_id: String? = null,
    val id: Int? = null,
    val location: String? = null,
    val login: String? = null,
    val name: String? = null,
    val node_id: String? = null,
    val organizations_url: String? = null,
    val public_gists: Int? = null,
    val public_repos: Int? = 0,
    val twitter_username: String? = null,
                      )