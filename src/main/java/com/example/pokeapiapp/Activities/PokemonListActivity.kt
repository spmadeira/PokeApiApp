package com.example.pokeapiapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokeapiapp.R
import me.sargunvohra.lib.pokekotlin.model.Pokemon

class PokemonListActivity : AppCompatActivity() {

    private lateinit var pokemon: List<Pokemon>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)
        
        val background = object : Thread(){
            override fun run(){
                LoadPokemons()
            }
            
            fun LoadPokemons(){
                
            }
        }
    }
}
