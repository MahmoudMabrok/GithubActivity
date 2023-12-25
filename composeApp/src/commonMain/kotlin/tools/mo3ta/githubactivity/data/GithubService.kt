package tools.mo3ta.githubactivity.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import tools.mo3ta.githubactivity.model.RepoDetails
import tools.mo3ta.githubactivity.model.UserDetails

interface GithubService {

    suspend fun getUserDetails(
            userName: String): UserDetails

    suspend fun loadUserRepos(
            userName: String,
            pageNumber: Int): List<RepoDetails>

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
                    },
                    githubKey,
                    enterprise,
                    isEnterprise
                                   )
        }
    }
}