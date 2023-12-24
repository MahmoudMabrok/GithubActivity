package tools.mo3ta.githubactivity.screens.userDetails

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import tools.mo3ta.githubactivity.model.RepoDetails
import tools.mo3ta.githubactivity.model.UserDetails

data class UserDetailsUiState(
    val isLoading:Boolean = false,
    val userData: UserDetails? = null,
    val repos: List<RepoDetails> =  emptyList()
){
    val totalStars = repos.sumOf { it.stargazers_count ?: 0 }
}

class UserDetailsViewModel(data: UserDetailsScreenData) : ViewModel() {

    private val githubKey = data.apiKey
    private val userName  = data.userName
    private val isEnterprise  = data.isEnterprise
    private val enterprise  = data.enterprise

    private val urlPrefix = prepareUrl(isEnterprise, enterprise)

    private val _uiState = MutableStateFlow(UserDetailsUiState())
    val uiState = _uiState.asStateFlow()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
            })
        }
    }

    init {
        loadData()
    }

    private fun loadData() {
        println("started")
        _uiState.update {
            it.copy(isLoading = true)
        }

        val handler = CoroutineExceptionHandler { _, throwable ->
            _uiState.update {
                println("Error : ${throwable.message}")
                it.copy(isLoading = false)
            }
        }

        viewModelScope.launch(handler) {
            try {
                val userData = async {  loadUserData() }
                val allRepos = async { loadAllRepos() }
                _uiState.update {
                    it.copy(userData = userData.await(), repos = allRepos.await() )
                }

            }catch (_: CancellationException){
                _uiState.update {
                    it.copy(isLoading = false)
                }
                return@launch
            }catch (e: JsonConvertException){
                _uiState.update {
                    it.copy(isLoading = false)
                }
                return@launch
            }finally {
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    private suspend fun loadAllRepos(): List<RepoDetails> {
        val allRepos = mutableListOf<RepoDetails>()
        var pageNumber = 1
        var repos = loadRepos(pageNumber)
        allRepos.addAll(repos)
        while (repos.isNotEmpty()){
            pageNumber += 1
            repos = loadRepos(pageNumber)
            allRepos.addAll(repos)
        }
        return allRepos
    }
    private suspend fun loadRepos(pageNumber: Int): List<RepoDetails> {
        return httpClient
            .get("https://$urlPrefix/users/${userName}/repos") {
                headers {
                    if(githubKey.isNotEmpty()){
                        append(HttpHeaders.Authorization, "Bearer $githubKey")
                    }
                }
                parameter("page", pageNumber.toString())
                parameter("per_page", 100.toString())
            }.body()
    }

    private suspend  fun loadUserData(): UserDetails {
        return httpClient
            .get("https://$urlPrefix/users/${userName}") {
                headers {
                    if(githubKey.isNotEmpty()){
                        append(HttpHeaders.Authorization, "Bearer $githubKey")
                    }
                }
            }.body()
    }

    private fun prepareUrl(isEnterprise: Boolean, enterprise: String): String {
        return if(isEnterprise){
            "github.${enterprise}.com/api/v3"
        }else{
            "api.github.com"
        }
    }

    override fun onCleared() {
        httpClient.close()
    }
}


