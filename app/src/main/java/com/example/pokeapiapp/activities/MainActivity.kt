package com.example.pokeapiapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.example.pokeapiapp.R
import com.example.pokeapiapp.data.PokemonData
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(baseContext, PokemonListActivity::class.java)
        startActivity(intent)
    }
}
