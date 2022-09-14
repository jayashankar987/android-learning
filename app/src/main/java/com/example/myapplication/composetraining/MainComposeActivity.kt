package com.example.myapplication.composetraining

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.ComposableTheme

class MainComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableTheme {
                androidx.compose.material.Surface {
                    showMessages(messageList = SampleData.conversationSample)
                }
            }
        }
    }

}

data class Message(val author: String, val message: String)

@Composable
fun showMessage(message: Message) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.img_20220911_132817),
            contentDescription = "profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .scale(1.1f)
                .border(width = 2.dp, color = MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val color by animateColorAsState(
            targetValue = if (isExpanded)
                MaterialTheme.colors.primary else MaterialTheme.colors.surface
        )

        Column(modifier = Modifier.clickable { isExpanded = (!isExpanded) }) {
            Text(
                text = message.author,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.secondaryVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            androidx.compose.material.Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = color,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.5.dp)) {
                Text(
                    text = message.message,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(5.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}

@Composable
fun showMessages(messageList: List<Message>) {
    LazyColumn {
        items(messageList) { message ->
            showMessage(message = message)
        }
    }
}

@Preview
@Composable
fun previewMessages() {
    showMessages(messageList = SampleData.conversationSample)
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "DARK MODE"
)
@Composable
fun previewMessageCard() {
    ComposableTheme {
        androidx.compose.material.Surface {
            showMessage(Message("Hi", "How is it going on"))
        }
    }
}