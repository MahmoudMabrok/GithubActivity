package tools.mo3ta.githubactivity.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import tools.mo3ta.githubactivity.model.UserDetails


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicUserData(userDetails: UserDetails?, stars: Int? = 0) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 16.dp)
          ) {


        userDetails?.avatar_url?.let {
            KamelImage(
                asyncPainterResource(it),
                "user avatar",
                modifier = Modifier
                    .width(200.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(8.dp, Color.Gray, CircleShape)
                    .align(Alignment.CenterHorizontally)
                      )
        }
        Spacer(modifier = Modifier.size(32.dp))
        userDetails?.name?.let {
            Text(
                it,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineLarge
                )
        }
        userDetails?.bio?.let {
            Text(
                it,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray
                )
        }
        userDetails?.email?.let {
            Text(
                it,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelSmall
                )
        }
        userDetails?.blog?.let {
            Text(
                it,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelSmall
                )
        }
        Spacer(modifier = Modifier.size(16.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
               ) {
            userDetails?.public_repos?.let {
                LabeledData("Public Repo", it.toString(), modifier = Modifier.wrapContentSize())
            }
            userDetails?.followers?.let {
                LabeledData("Follower", it.toString(), modifier = Modifier.wrapContentSize())
            }
            userDetails?.following?.let {
                LabeledData("Following", it.toString(), modifier = Modifier.wrapContentSize())
            }

            stars?.let {
                LabeledData(
                    "Stars",
                    it.toString(),
                    modifier = Modifier.wrapContentSize()
                           )
            }

            userDetails?.company?.let {
                LabeledData("Company", it, modifier = Modifier.wrapContentSize())
            }

            userDetails?.location?.let {
                LabeledData("Location", it, modifier = Modifier.wrapContentSize())
            }
        }
    }
}