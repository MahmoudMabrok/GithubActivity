package tools.mo3ta.githubactivity.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import tools.mo3ta.githubactivity.model.RepoDetails
import tools.mo3ta.githubactivity.model.UserDetails
import tools.mo3ta.githubactivity.model.UserEvent

interface GithubService {

    suspend fun getUserDetails(
        userName: String
                              ): UserDetails

    suspend fun loadUserRepos(
        userName: String,
        pageNumber: Int
                             ): List<RepoDetails>

    suspend fun loadUserEvents(
        userName: String,
        pageNumber: Int
                              ): List<UserEvent>

    fun closeClient()

    companion object {
        fun create(
            githubKey: String,
            enterprise: String,
            isEnterprise: Boolean = false,
                  ):
                GithubService {
            return GithubServiceImp(
                httpClient = HttpClient {
                    install(ContentNegotiation) {
                        json(json = Json {
                            ignoreUnknownKeys = true
                        })
                    }
                    install(Logging) {
                        level = io.ktor.client.plugins.logging.LogLevel.ALL
                    }
                },
                githubKey,
                enterprise,
                isEnterprise
                                   )
        }
    }
}