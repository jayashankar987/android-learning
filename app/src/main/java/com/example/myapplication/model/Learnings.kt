package com.example.myapplication.model

import com.example.myapplication.model.ItemType.COMPOSABLE
import com.example.myapplication.model.ItemType.DATASTORE
import com.example.myapplication.model.ItemType.EXPANDABLE_LIST_USING_COMPOSABLE

object Learnings {
    val learningsList = listOf(
        LearningDetails(id = EXPANDABLE_LIST_USING_COMPOSABLE, title = "Expandable list using composable"),
        LearningDetails(id = DATASTORE, title = "Data Store"),
        LearningDetails(id = COMPOSABLE, title = "Composable")
    )
}