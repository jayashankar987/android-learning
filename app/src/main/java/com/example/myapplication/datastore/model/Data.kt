package com.example.myapplication.datastore.model

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.myapplication.model.ItemType.DATASTORE_PREFERENCE
import com.example.myapplication.model.ItemType.DATASTORE_PROTO

object Data {
    val items = listOf(
        DataStoreDetails(DATASTORE_PREFERENCE, "Preference Data Store"),
        DataStoreDetails(DATASTORE_PROTO, "Proto Data Store")
    )
    private const val KEY_EMAIL = "EMAIL"
    private const val KEY_NAME = "DEV_NAME"
    const val USER_PREF = "user_pref"

    val NAME_KEY = stringPreferencesKey(KEY_NAME)
    val EMAIL_KEY = stringPreferencesKey(KEY_EMAIL)
}