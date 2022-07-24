package com.sadapay.assessment.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.sadapay.assessment.data.PrefsManager.PreferencesKeys.IS_DARK_MODE
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PrefsManager @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.createDataStore(name = PREFS)


    private object PreferencesKeys {
        val IS_DARK_MODE = preferencesKey<Boolean>(_IS_DARK_MODE)
    }

    companion object {
        const val PREFS: String = "PREFS"
        const val _IS_DARK_MODE: String = "DARK_MODE"
    }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        dataStore.edit { _dataStore ->
            _dataStore[IS_DARK_MODE] = isDarkMode
        }
    }

    suspend fun getDarkMode(): Boolean {
        val preferences = dataStore.data.first()
        return if (preferences[IS_DARK_MODE] == null) {
            false
        } else preferences[IS_DARK_MODE]!!
    }


}