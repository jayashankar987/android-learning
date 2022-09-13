package com.example.myapplication.datastore.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.base.AppTheme
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.TopBar
import com.example.myapplication.datastore.model.Data
import com.example.myapplication.model.ItemType
import com.example.myapplication.model.ItemType.DATASTORE_PREFERENCE
import com.example.myapplication.model.ItemType.DATASTORE_PROTO
import com.example.myapplication.ui.widget.ListItem

class DataStoreActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = this
        setContent {
            AppTheme {
                Scaffold(topBar = { TopBar() }) { padding ->
                    MainContent(paddingValues = padding) {
                        val intent = Intent(context, DataStoreDetailsActivity::class.java)
                        when (it) {
                            DATASTORE_PREFERENCE -> {
                                intent.apply {
                                    action = "data_store_preference"
                                }
                            }
                            DATASTORE_PROTO -> {
                                intent.apply {
                                    action = "data_store_proto"
                                }
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
fun MainContent(paddingValues: PaddingValues, itemType: (ItemType) -> Unit) {
    val dataStores = remember { Data.items }
    LazyColumn(contentPadding = paddingValues) {
        items(dataStores) { dataStore ->
            Box(modifier = Modifier.clickable { itemType(dataStore.itemType) }) {
                ListItem.DataStoreListItemCard(dataStoreDetails = dataStore)
            }
        }
    }
}

@Preview
@Composable
fun MainContentPreview() {
    MainContent(paddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {

    }
}