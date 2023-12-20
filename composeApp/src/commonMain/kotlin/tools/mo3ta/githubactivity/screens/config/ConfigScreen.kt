package tools.mo3ta.githubactivity.screens.config

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.russhwolf.settings.Settings
import tools.mo3ta.githubactivity.data.Keys
import tools.mo3ta.githubactivity.screens.userDetails.UserDetailsScreen
import tools.mo3ta.githubactivity.screens.userDetails.UserDetailsScreenData
import tools.mo3ta.githubactivity.theme.LocalThemeIsDark



data object ConfigScreen : Screen {
    val settings = Settings()

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var githubKey by rememberSaveable { mutableStateOf(settings.getString(Keys.API_KEY, ""))}
        var userName by rememberSaveable { mutableStateOf(settings.getString(Keys.USER_NAME, ""))}
        var isEnterprise by rememberSaveable { mutableStateOf(settings.getBoolean(Keys.IS_ENTERPRISE, false))}
        var enterprise by rememberSaveable { mutableStateOf(settings.getString(Keys.ENTERPRISE, ""))}
        var isDark by LocalThemeIsDark.current

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Configurations",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge,
                )

                Spacer(modifier = Modifier.weight(1.0f))

                IconButton(
                    onClick = { isDark = !isDark }
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp).size(20.dp),
                        imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                        contentDescription = null
                    )
                }
            }

            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("User Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )


            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Is Enterprise",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge,
                )

                Spacer(modifier = Modifier.weight(1.0f))

                Checkbox(checked = isEnterprise, onCheckedChange = { state -> isEnterprise = state})
            }


            if (isEnterprise){
                OutlinedTextField(
                    value = githubKey,
                    onValueChange = { githubKey = it },
                    label = { Text("Token") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                )


                OutlinedTextField(
                    value = enterprise,
                    onValueChange = { enterprise = it },
                    label = { Text("Enterprise Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                )
            }

            Button(
                onClick = {
                    saveData(userName, githubKey, isEnterprise, enterprise)
                    navigator.push(
                        UserDetailsScreen(
                            UserDetailsScreenData(
                                apiKey = githubKey,
                                isEnterprise = isEnterprise,
                                enterprise = enterprise,
                                userName = userName)))
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Fire")
            }
        }

    }

    private fun saveData(userName: String, githubKey: String, isEnterprise: Boolean, enterprise: String) {
        with(settings){
            putString(Keys.USER_NAME, userName)
            putString(Keys.API_KEY, githubKey)
            putString(Keys.ENTERPRISE, enterprise)
            putBoolean(Keys.IS_ENTERPRISE, isEnterprise)
        }
    }

}