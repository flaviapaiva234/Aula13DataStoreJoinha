package com.example.aula13datastoreflavia

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import java.util.prefs.Preferences

object DataStoreManager {

    // Variaável como o contexto global do app
    private lateinit var application: Application

    fun init(application: Application){
        this.application = application // inicializando o contexto
    }
//Cria a instancia do texto global no DatatStore
private val Context.preferencesDataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "USER_INFO")

    // Criar a função que Salva o numero do usuário em uma chave "NAME"
  suspend fun setNameUserDataStore(name: String){
      application.preferencesDataStore.edit { preferences ->
                 preferences[stringPreferencesKey("NAME")] = name
      }
  }

    suspend fun setAgeUserDataStore(age: Int){
        application.preferencesDataStore.edit { preferences ->
            preferences[intPreferencesKey("IDADE")] = age
        }
    }

    suspend fun setPaisUserDataStore(pais: String){
        application.preferencesDataStore.edit { preferences ->
            preferences[stringPreferencesKey("PAIS")] = pais
        }
    }

    //Vai fazer a leitura do dado salvo na chave "NAME"
    suspend fun readNameUserDataStore(): String {
        return application.preferencesDataStore.data.first()[stringPreferencesKey("NAME")] ?: ""
    }
    //Vai fazer a leitura do dado salvo na chave "IDADE"
    suspend fun readAgeUserDataStore(): Int {
        return application.preferencesDataStore.data.first()[intPreferencesKey("IDADE")] ?: 0
    }
    //Vai fazer a leitura do dado salvo na chave "Pais"
    suspend fun readPaisUserDataStore(): String {
        return application.preferencesDataStore.data.first()[stringPreferencesKey("PAIS")] ?: ""
    }
      // recebe o valor
      //função tipo T é um tipo genérico, Any é que pode ser qualquer coisa
      suspend fun <T : Any> setDataStore( // como é genérico, agente não sabe qual Key vai ser usada (string preference key, int key, boolean key, etc..) o usuário que vai falar quando ele colocar o dado
          preferencesKey: androidx.datastore.preferences.core.Preferences.Key<T>, // T é do tipo que o usuario passar
          value: T
      ) {
          application.preferencesDataStore.edit { preferences ->
              preferences[preferencesKey] = value
          }
      }

    // Para ler o valor, também tem que criar uma função genérica
    suspend fun <T: Any> readDataStore(
        preferencesKey: androidx.datastore.preferences.core.Preferences.Key<T>
    ): T? {   // pode retornar nulo
        return application.preferencesDataStore.data.first()[preferencesKey]
    }
}