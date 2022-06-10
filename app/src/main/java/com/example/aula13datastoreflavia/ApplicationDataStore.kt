package com.example.aula13datastoreflavia

import android.app.Application

class ApplicationDataStore: Application() {

    override fun onCreate() {
        super.onCreate()
        DataStoreManager.init(this) //Inicializa o DataStore como contexto Global do app

    }

}