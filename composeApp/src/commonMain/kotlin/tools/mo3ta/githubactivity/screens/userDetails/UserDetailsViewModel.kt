package tools.mo3ta.githubactivity.screens.userDetails

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tools.mo3ta.githubactivity.data.GithubService
import tools.mo3ta.githubactivity.model.RepoDetails
import tools.mo3ta.githubactivity.model.UserDetails

data class UserDetailsUiState(
        val isLoading: Boolean = false,
        val userData: UserDetails? = null,
        val repos: List<RepoDetails> = emptyList()
                             ) {
    val totalStars = repos.sumOf {
        it.stargazers_count ?: 0
    }
}

class UserDetailsViewModel(private val userName: String,
                           private val githubApi: GithubService) : ViewModel
                                                                   () {
    private val _uiState = MutableStateFlow(UserDetailsUiState())
    val uiState = _uiState.asStateFlow()


    init {
        loadData()
    }

    private fun loadData() {
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
                val userData = async { githubApi.getUserDetails(userName) }
                val allRepos = async { loadAllRepos() }
                _uiState.update {
                    it.copy(userData = userData.await(),
                            repos = allRepos.await())
                }

            } catch (_: CancellationException) {
                _uiState.update {
                    it.copy(isLoading = false)
                }
                return@launch
            } catch (e: JsonConvertException) {
                _uiState.update {
                    it.copy(isLoading = false)
                }
                return@launch
            } finally {
                _uiState.update {
                    it.copy(
                            isLoading = false
                           )
                }
            }
        }
    }

    suspend fun loadAllRepos(): List<RepoDetails> {
        val allRepos = mutableListOf<RepoDetails>()
        var pageNumber = 1
        var repos = githubApi.loadUserRepos(userName,
                                            pageNumber)
        allRepos.addAll(repos)
        while (repos.isNotEmpty()) {
            pageNumber += 1
            repos = githubApi.loadUserRepos(userName,
                                            pageNumber)
            allRepos.addAll(repos)
        }
        return allRepos
    }

    override fun onCleared() {
        githubApi.closeClient()
    }
}


