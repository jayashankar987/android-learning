package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.base.AppTheme
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.expandablelist.ExpandableListActivity
import com.example.myapplication.model.ItemType
import com.example.myapplication.model.ItemType.EXPANDABLE_LIST_USING_COMPOSABLE
import com.example.myapplication.model.Learnings
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.widget.ListItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContent {
            AppTheme {

                Column {

                    TopAppBar(title = { "Learning App" })

                    // A surface container using the 'background' color from the theme
                    LearningContent {
                        when (it) {
                            EXPANDABLE_LIST_USING_COMPOSABLE -> startActivity(
                                Intent(context, ExpandableListActivity::class.java)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LearningContent(itemType: (ItemType) -> Unit) {
    val learnings = remember { Learnings.learningsList }
    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)) {
        items(learnings) {
            Surface(modifier = Modifier.clickable { itemType(it.id) }) {
                ListItem.ListItemCard(learningDetails = it)
            }
        }
    }
}