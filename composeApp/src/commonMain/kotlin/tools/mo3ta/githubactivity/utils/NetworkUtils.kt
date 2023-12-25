package tools.mo3ta.githubactivity.utils

object NetworkUtils {
    fun prepareUrl(isEnterprise: Boolean,
                   enterprise: String): String {
        return if (isEnterprise) {
            "github.${enterprise}.com/api/v3"
        } else {
            "api.github.com"
        }
    }
}