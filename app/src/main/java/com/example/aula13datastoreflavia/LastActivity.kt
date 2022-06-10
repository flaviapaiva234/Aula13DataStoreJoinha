package com.example.aula13datastoreflavia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.aula13datastoreflavia.databinding.ActivityLastBinding
import com.example.aula13datastoreflavia.databinding.ActivityMainBinding
import kotlinx.coroutines.runBlocking

class LastActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLastBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

          show()
        setListeners3()


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


        }
        Toast.makeText(this, "Nome: $nome, Idade: $idade, Cidade: $cidade, Pais: $pais", Toast.LENGTH_SHORT).show()

        //  println("Nome: $nome, Idade: $idade, Cidade: $cidade, País: $pais")
    }

    private fun setListeners3() {
        binding.btnVolte.setOnClickListener {

            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }

        }
    }
}