package com.example.pokeapiapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapiapp.R
import com.example.pokeapiapp.adapter.PokemonClickListener
import com.example.pokeapiapp.adapter.PokemonFavoritesAdapter
import com.example.pokeapiapp.data.PokemonData
import kotlinx.android.synthetic.main.activity_pokemon_favorites.*
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.Pokemon

class PokemonFavoritesActivity : AppCompatActivity(), PokemonClickListener {

    override fun onDetailClick(view: View, index: Int) {
        val detailIntent = Intent(view.context,PokemonDetailActivity::class.java)
        val detailIndex = favorites[index]
        detailIntent.putExtra("pokemon_id",detailIndex)
        ActivityCompat.startActivity(view.context, detailIntent, null)
    }

    override fun onFavoriteClick(view: View, index: Int) {
        PokemonData.RemoveFavorite(adapter.pokemon[index].id)
        adapter.pokemon.removeAt(index)
        adapter.notifyDataSetChanged()
        Log.i("fav", PokemonData.favorites)
    }

    private lateinit var adapter: PokemonFavoritesAdapter
    private lateinit var favorites: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_favorites)
        favorites = intent.getIntArrayExtra("favorites")!!

        pokemon_recyclerview_favorites.layoutManager = LinearLayoutManager(this)
        adapter = PokemonFavoritesAdapter(this,this, mutableListOf())
        pokemon_recyclerview_favorites.adapter = adapter

        val background = object : Thread(){
            override fun run() {
                val pokemonList = mutableListOf<Pokemon>()
                val client = PokeApiClient()
                favorites.forEach {
                    Log.i("aaa",it.toString())
                    val poke = client.getPokemon(it+1)
                    pokemonList.add(poke)
                }

                runOnUiThread{
                   LoadPokemon(pokemonList)
                }
            }
        }

        background.start()
    }

    fun LoadPokemon(pokemon: MutableList<Pokemon>){
        adapter.pokemon.addAll(pokemon)
        adapter.notifyDataSetChanged()
    }
}
