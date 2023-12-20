package tools.mo3ta.githubactivity.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LabeledData(title:String , value: String) {

    Row (modifier = Modifier.padding(16.dp)){
        Text("$title : ", modifier = Modifier.weight(1.5f))
        Text(value, modifier = Modifier.weight(4f))
    }
}