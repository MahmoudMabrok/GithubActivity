package tools.mo3ta.githubactivity.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import tools.mo3ta.githubactivity.model.RepoDetails
import tools.mo3ta.githubactivity.model.UserDetails
import tools.mo3ta.githubactivity.model.UserEvent
import tools.mo3ta.githubactivity.utils.NetworkUtils.prepareUrl

class GithubServiceImp(
    val httpClient: HttpClient,
    val githubKey: String,
    enterprise: String,
    isEnterprise: Boolean = false
                      ) : GithubService {

    val baseUrl = prepareUrl(
        isEnterprise,
        enterprise
                            )

    override suspend fun getUserDetails(
        userName: String
                                       ): UserDetails {
        return httpClient
            .get("https://$baseUrl/users/${userName}") {
                headers {
                    if (githubKey.isNotEmpty()) {
                        append(
                            HttpHeaders.Authorization,
                            "Bearer $githubKey"
                              )
                    }
                }
            }.body()
    }

    override suspend fun loadUserRepos(
        userName: String,
        pageNumber: Int
                                      ): List<RepoDetails> {
        return httpClient
            .get("https://$baseUrl/users/${userName}/repos") {
                headers {
                    if (githubKey.isNotEmpty()) {
                        append(
                            HttpHeaders.Authorization,
                            "Bearer $githubKey"
                              )
                    }
                }
                parameter(
                    "page",
                    pageNumber.toString()
                         )
                parameter(
                    "per_page",
                    100.toString()
                         )
            }.body()
    }

    override suspend fun loadUserEvents(userName: String, pageNumber: Int): List<UserEvent> {
        return httpClient
            .get("https://$baseUrl/users/${userName}/events") {
                headers {
                    if (githubKey.isNotEmpty()) {
                        append(
                            HttpHeaders.Authorization,
                            "Bearer $githubKey"
                              )
                    }
                }
                parameter(
                    "page",
                    pageNumber.toString()
                         )
                parameter(
                    "per_page",
                    100.toString()
                         )
            }.body()
    }


    override fun closeClient() {
        httpClient.close()
    }
}