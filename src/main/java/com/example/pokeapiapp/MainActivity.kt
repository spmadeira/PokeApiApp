package com.example.pokeapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient

class MainActivity : AppCompatActivity() {

    private var client: PokeApiClient = PokeApiClient()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }
}
