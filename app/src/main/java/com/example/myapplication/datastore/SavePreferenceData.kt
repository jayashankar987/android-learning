package com.example.myapplication.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplication.datastore.model.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class SavePreferenceData(private val context: Context) {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Data.USER_PREF)

    }

    suspend fun storeUserInfo(name: String, email: String) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { mutablePreferences ->
                mutablePreferences[Data.NAME_KEY] = name
                mutablePreferences[Data.EMAIL_KEY] = email
            }
        }
    }

    fun getUserNameFlow(): Flow<String> = context.dataStore.data.map { it[Data.NAME_KEY] ?: "" }
    fun getEmailAddress(): Flow<String> = context.dataStore.data.map { it[Data.EMAIL_KEY] ?: "" }

}