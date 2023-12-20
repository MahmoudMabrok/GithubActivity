package tools.mo3ta.githubactivity.screens.userDetails

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
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
data class UserDetailsUiState(
    val isLoading:Boolean = false,
    val userData: UserDetailsScreen? = null
)

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
        _uiState.update {
            it.copy(isLoading = true)
        }

        val handler = CoroutineExceptionHandler { _, _ ->
            _uiState.update {
                it.copy(isLoading = false)
            }
        }

        viewModelScope.launch(handler) {
            try {
                val userData = async {  loadUserData() }.await()
                _uiState.update {
                    it.copy(userData = userData)
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

    private suspend  fun loadUserData(): UserDetailsScreen {
        return httpClient
            .get("https://$urlPrefix/repos/${userName}/pulls") {
                headers {
                    if(isEnterprise){
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


