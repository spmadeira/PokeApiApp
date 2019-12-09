package com.example.pokeapiapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.pokeapiapp.R
import com.example.pokeapiapp.data.PokemonData

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        PokemonData.StartLoadAllData(baseContext)
        Handler().postDelayed({

            val intent = Intent(this, PokemonListActivity::class.java)
            startActivity(intent)
        },   4000)
    }
}

