package com.scherzolambda.labelrun

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.scherzolambda.labelrun.core.config.DataStoreHelper
import com.scherzolambda.labelrun.core.config.EnvConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LabelRunApplication : Application() {
    private val Context.dataStore by preferencesDataStore(name = "app_preferences")

    override fun onCreate() {
        super.onCreate()
        // Inicializa o DataStoreHelper com o DataStore antes de qualquer requisição
        EnvConfig.load(this)
        DataStoreHelper.initialize(this.dataStore)
    }
}