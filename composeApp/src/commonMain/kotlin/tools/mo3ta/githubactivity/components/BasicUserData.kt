package tools.mo3ta.githubactivity.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import tools.mo3ta.githubactivity.model.UserDetails

@Composable
fun BasicUserData(userDetails: UserDetails?) {
    Column {
        userDetails?.name?.let { LabeledData("Name", it) }
        userDetails?.bio?.let { LabeledData("Bio", it) }
        userDetails?.email?.let { LabeledData("Email", it) }
        userDetails?.company?.let { LabeledData("Company", it) }
        userDetails?.blog?.let { LabeledData("Blog", it) }
        userDetails?.location?.let { LabeledData("Location", it) }
        userDetails?.public_repos?.let { LabeledData("Public Repo", it.toString()) }
        userDetails?.public_gists?.let { LabeledData("Public Gists", it.toString()) }
        userDetails?.followers?.let { LabeledData("Followers", it.toString()) }
        userDetails?.following?.let { LabeledData("Following", it.toString()) }
    }
}