package com.tambi.core.storage.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

// DataStore as Singleton
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class DataStoreStorage @Inject constructor(@ApplicationContext val context: Context) : Storage {

    private val favorite = stringPreferencesKey(Storage.KEY_FAVORITES)

    override suspend fun setFavorites(favorites: List<String>) {
        context.dataStore.edit { preferences ->
            preferences[favorite] = Json.encodeToString(favorites)
        }
    }

    override suspend fun getFavorites(): Flow<List<String>> {
        return context.dataStore.data.map { preferences ->
            try {
                preferences[favorite]?.let { Json.decodeFromString(it) } ?: listOf()
            } catch (e: Exception) {
                listOf()
            }
        }
    }
}
