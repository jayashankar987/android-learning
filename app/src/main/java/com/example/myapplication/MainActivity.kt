package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.myapplication.base.AppTheme
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.TopBar
import com.example.myapplication.datastore.screen.DataStoreActivity
import com.example.myapplication.expandablelist.screen.ExpandableListActivity
import com.example.myapplication.model.ItemType
import com.example.myapplication.model.ItemType.EXPANDABLE_LIST_USING_COMPOSABLE
import com.example.myapplication.model.LearningViewModel
import com.example.myapplication.ui.widget.ListItem

class MainActivity : BaseActivity() {

    private val viewModel by viewModels<LearningViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContent {
            AppTheme {
                Scaffold(topBar = { TopBar() }) { padding ->
                    // A surface container using the 'background' color from the theme
                    var intent: Intent
                    LearningContent(paddingValues = padding, viewModel = viewModel) {
                        when (it) {
                            EXPANDABLE_LIST_USING_COMPOSABLE -> {
                                intent = Intent(context, ExpandableListActivity::class.java)
                            }

                            else -> {
                                intent = Intent(context, DataStoreActivity::class.java)
                            }
                        }
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun LearningContent(
    paddingValues: PaddingValues,
    viewModel: LearningViewModel,
    itemType: (ItemType) -> Unit
) {
    val learnings by viewModel.learningList.collectAsState()
    LazyColumn(contentPadding = paddingValues) {
        itemsIndexed(learnings) { _, learning ->
            Box(modifier = Modifier.clickable { itemType(learning.id) }) {
                ListItem.LearningListItemCard(learningDetails = learning)
            }
        }
    }
}
