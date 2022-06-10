package com.example.aula13datastoreflavia

import android.app.Activity
import android.content.Intent
import android.opengl.ETC1.isValid
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.aula13datastoreflavia.databinding.ActivityLoginBinding
import kotlinx.coroutines.runBlocking

class LoginActivity : AppCompatActivity() {

    //1. Só criar o binding para o layout
    private lateinit var binding: ActivityLoginBinding

    //2. e associar a activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
     //4.
        setUpListeners()
        setListeners3()
    }

    //3.
    private fun setUpListeners() {
        binding.btnLogin.setOnClickListener {
            if(isValid()){
                saveUser()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }

        }
    }


    //5.
   //o ideal é chamar essa função em background
    private fun saveUser(){
        //como as funções do DataStore são assíncronas, não da para chamar direto
        runBlocking {
            DataStoreManager.setNameUserDataStore( // chamar as funções
               binding.etNome.text.toString()   // pegar o name lá no binding
            )
            DataStoreManager.setAgeUserDataStore(
                binding.etIdade.text.toString().toInt()
            )
            DataStoreManager.setDataStore(
                preferencesKey = stringPreferencesKey("CIDADE"),
                value = binding.etCidade.text.toString()
            )
            DataStoreManager.setPaisUserDataStore(
                binding.etPais.text.toString()
            )
            DataStoreManager.setDataStore(preferencesKey = booleanPreferencesKey("HAS_LOGIN"), true)
        }
    }

    // validação para verificar se o campo não está vazio
    private fun isValid(): Boolean {
        return (binding.etNome.text.isNotEmpty() and binding.etIdade.text.isNotEmpty() and binding.etCidade.text.isNotEmpty())
    }
    private fun setListeners3() {
        binding.btnCancelar.setOnClickListener {

            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }

        }
    }

}