package tools.mo3ta.githubactivity.screens.userDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import tools.mo3ta.githubactivity.components.BasicUserData
import tools.mo3ta.githubactivity.components.LabeledData
import tools.mo3ta.githubactivity.components.Loading
import tools.mo3ta.githubactivity.data.GithubService

data class UserDetailsScreenData(val apiKey: String,
                                 val userName: String,
                                 val isEnterprise: Boolean,
                                 val enterprise: String)


data class UserDetailsScreen(
        val data: UserDetailsScreenData) : Screen {

    @Composable
    override fun Content() {
        val viewModel = getViewModel(Unit,
                                     viewModelFactory {
                                         UserDetailsViewModel(data.userName,
                                                              GithubService.create(data.apiKey,
                                                                                   data.enterprise,
                                                                                   data.isEnterprise))
                                     })
        val uiState by viewModel.uiState.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {
            if (uiState.isLoading) {
                Loading(Modifier.fillMaxSize(),
                        "User Data for ${data.userName}")
            } else {
                uiState.totalStars.takeIf { it > 0 }?.let {
                    LabeledData("TotalStars",
                                it.toString())
                }
                BasicUserData(uiState.userData)
            }
        }
    }
}
