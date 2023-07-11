package com.tambi.core.storage.preference

import kotlinx.coroutines.flow.Flow

interface Storage {

    companion object {
        internal const val KEY_FAVORITES = "key_favorites"
    }

    suspend fun setFavorites(favorites: List<String>)
    suspend fun getFavorites(): Flow<List<String>>
}
