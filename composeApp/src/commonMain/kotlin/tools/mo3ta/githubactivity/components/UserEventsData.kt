package tools.mo3ta.githubactivity.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tools.mo3ta.githubactivity.model.UserEvents


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserEventsData(events: UserEvents?) {
    events?.let {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
              ) {
            Text("UserEvents")

            Spacer(modifier = Modifier.size(16.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.Center
                   ) {
                LabeledData(
                    "Total Push",
                    events.pullRequestOpened.toString(),
                    modifier = Modifier.wrapContentSize()
                           )
                LabeledData(
                    "PR created",
                    events.pullRequestOpened.toString(),
                    modifier = Modifier.wrapContentSize()
                           )
                LabeledData(
                    "PR Approved",
                    events.pullRequestApproved.toString(),
                    modifier = Modifier.wrapContentSize()
                           )
                LabeledData(
                    "Review Comments",
                    events.reviewCommentAdded.toString(),
                    modifier = Modifier.wrapContentSize()
                           )
            }
        }

    }
}
