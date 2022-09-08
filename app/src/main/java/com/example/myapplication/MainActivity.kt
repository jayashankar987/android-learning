package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.base.AppTheme
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.TopBar
import com.example.myapplication.expandablelist.screen.ExpandableListActivity
import com.example.myapplication.model.ItemType
import com.example.myapplication.model.ItemType.EXPANDABLE_LIST_USING_COMPOSABLE
import com.example.myapplication.model.Learnings
import com.example.myapplication.ui.widget.ListItem

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContent {
            AppTheme {

                Column {
                    TopBar()
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
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(learnings) {
            Surface(modifier = Modifier.clickable { itemType(it.id) }) {
                ListItem.ListItemCard(learningDetails = it)
            }
        }
    }
}