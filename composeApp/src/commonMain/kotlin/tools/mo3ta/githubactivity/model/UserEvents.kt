package tools.mo3ta.githubactivity.model

data class UserEvents(
    val pullRequestOpened: Int = 0,
    val reviewCommentAdded: Int = 0,
    val pullRequestApproved: Int = 0,
    val pushCounts: Int = 0,
                     )
