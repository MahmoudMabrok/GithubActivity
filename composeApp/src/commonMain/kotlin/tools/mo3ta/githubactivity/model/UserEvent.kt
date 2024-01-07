package tools.mo3ta.githubactivity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserEvent(
    val created_at: String? = null,
    val payload: Payload? = null,
    @SerialName("public")
    val isPublic: Boolean? = null,
    val type: String
                    ) {

    fun isPullRequest(): Boolean {
        return type == "PullRequestEvent" && payload?.isOpened() ?: false
    }

    fun isApproved(): Boolean {
        return type == "PullRequestReviewEvent" && payload?.isApproved() ?: false
    }

    fun isReviewComment(): Boolean {
        return type == "PullRequestReviewCommentEvent" && payload?.isCreated() ?: false
    }

    fun isPush(): Boolean {
        return type == "PushEvent"
    }
}


@Serializable
data class Payload(val action: String? = null, val review: Review? = null) {
    fun isCreated(): Boolean {
        return action == "created"
    }

    fun isOpened(): Boolean {
        return action == "opened"
    }


    fun isApproved(): Boolean {
        return isCreated() && review?.isApproved() ?: false
    }
}

@Serializable
data class Review(val state: String? = "") {
    fun isApproved(): Boolean {
        return state == "approved"
    }
}