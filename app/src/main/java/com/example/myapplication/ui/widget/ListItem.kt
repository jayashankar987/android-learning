package com.example.myapplication.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.datastore.model.DataStoreDetails
import com.example.myapplication.model.LearningDetails

object ListItem {

    @Composable
    fun LearningListItemCard(learningDetails: LearningDetails) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            backgroundColor = Color.LightGray,
            shape = RoundedCornerShape(corner = CornerSize(10.dp))
        ) {
            Row(modifier = Modifier.padding(20.dp)) {
                Column(modifier = Modifier.weight(1f), Arrangement.Center) {
                    Text(
                        text = learningDetails.title,
                        style = TextStyle(color = Color.Black, fontSize = 22.sp)
                    )
                }
            }
        }
    }

    @Composable
    fun DataStoreListItemCard(dataStoreDetails: DataStoreDetails) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            backgroundColor = Color.LightGray,
            shape = RoundedCornerShape(corner = CornerSize(8.dp))
        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                Column(modifier = Modifier.weight(1f), Arrangement.Center) {
                    Text(text = dataStoreDetails.title, style = TextStyle(color = Color.Black, fontSize = 18.sp))
                }

            }
        }
    }

}