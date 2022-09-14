package com.example.myapplication.model

data class LearningDetails(val id: ItemType, val title: String)

enum class ItemType {
    EXPANDABLE_LIST_USING_COMPOSABLE,
    DATASTORE,
    DATASTORE_PREFERENCE,
    DATASTORE_PROTO,
    COMPOSABLE
}