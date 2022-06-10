package com.example.aula13datastoreflavia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.aula13datastoreflavia.databinding.ActivityMainBinding
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        show()


    }



    private fun show(){
        val nome: String
        val idade: Int
        val cidade: String
        val pais: String

        runBlocking {
            nome = DataStoreManager.readNameUserDataStore()
            idade = DataStoreManager.readAgeUserDataStore()
            cidade = DataStoreManager.readDataStore(stringPreferencesKey("CIDADE")) ?: "" // como pode ser nulo, tem que fazer o tratamento de internário
            pais = DataStoreManager.readPaisUserDataStore()

            setListeners()
            setListeners2()
        }
        Toast.makeText(this, "Nome: $nome, Idade: $idade, Cidade: $cidade, Pais: $pais", Toast.LENGTH_SHORT).show()

      //  println("Nome: $nome, Idade: $idade, Cidade: $cidade, País: $pais")
    }

    private fun setListeners() {
        binding.btnParaOLogin.setOnClickListener {

            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }

        }
    }

    private fun setListeners2() {
        binding.btnParaLast.setOnClickListener {

            Intent(this, LastActivity::class.java).also {
                startActivity(it)
            }

        }
    }

}