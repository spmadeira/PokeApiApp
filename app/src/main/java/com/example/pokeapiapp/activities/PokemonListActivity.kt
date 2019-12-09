package com.example.pokeapiapp.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiapp.R
import com.example.pokeapiapp.adapter.PokemonListAdapter
import com.example.pokeapiapp.adapter.PokemonClickListener
import com.example.pokeapiapp.data.IPokeList
import com.example.pokeapiapp.data.PokemonData
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.Pokemon

class PokemonListActivity : AppCompatActivity(), PokemonClickListener, IPokeList {
    override fun onDetailClick(view: View, index: Int) {
        val detailIntent = Intent(view.context,PokemonDetailActivity::class.java)
        detailIntent.putExtra("pokemon_id",index)
        var animacao : ActivityOptionsCompat
        animacao = ActivityOptionsCompat.makeCustomAnimation(view.context,R.anim.fade_in,R.anim.fade_out)
        ActivityCompat.startActivity(view.context, detailIntent, animacao.toBundle())
    }

    override fun onFavoriteClick(view: View, index: Int) {
        if (!PokemonData.IsFavorited(index)){
            PokemonData.AddFavorite(index)
            UpdateList()
            Log.i("fav", PokemonData.favorites)
        } else {
            PokemonData.RemoveFavorite(index)
            UpdateList()
            Log.i("fav", PokemonData.favorites)
        }
    }

    override fun UpdateList() {
        runOnUiThread{
            adapter.UpdateList()
        }
    }

    private lateinit var adapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

        val pokemonList: RecyclerView = findViewById(R.id.pokemon_recyclerview_list)
        //pokemonList.layoutManager = LinearLayoutManager(this)
        pokemonList.layoutManager = GridLayoutManager(this,2)

        adapter = PokemonListAdapter(this, this)
        pokemonList.adapter = adapter

        pokemon_list_favorites_button.setOnClickListener {
            GoToFavorites()
        }
    }

    fun GoToFavorites(){
        val favoriteIntent = Intent(this,PokemonFavoritesActivity::class.java)
        val pokemon = PokemonData.GetFavorites()
        favoriteIntent.putExtra("favorites",pokemon.toIntArray())
        ActivityCompat.startActivity(this,favoriteIntent,null)
    }

    override fun onResume() {
        super.onResume()
        PokemonData.pokeList = this
    }

    override fun onPause() {
        super.onPause()
        PokemonData.pokeList = null
    }
}
