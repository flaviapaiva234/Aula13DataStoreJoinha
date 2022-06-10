package com.example.aula13datastoreflavia

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.example.aula13datastoreflavia.databinding.ActivitySplashBinding
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            verifyUser()
            finish()
        },2000)
    }

    private fun verifyUser(){ // não dá para chamar direto, por isso tem que colocar a runBlocking
        runBlocking {
            val hasLogin = DataStoreManager.readDataStore(booleanPreferencesKey("HAS_LOGIN")) ?: false
            if (!hasLogin) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}