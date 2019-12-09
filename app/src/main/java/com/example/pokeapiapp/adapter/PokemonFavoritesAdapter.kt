package com.example.pokeapiapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiapp.R
import com.example.pokeapiapp.data.PokemonData
import com.squareup.picasso.Picasso
import me.sargunvohra.lib.pokekotlin.model.Pokemon

class PokemonFavoritesAdapter (val context: Context, val listener: PokemonClickListener, val pokemon: MutableList<Pokemon>): RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {
    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListAdapter.PokemonViewHolder {
        val view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false)
        return PokemonListAdapter.PokemonViewHolder(view, context, listener)
    }

    override fun getItemCount(): Int {
        return pokemon.size
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.PokemonViewHolder, position: Int) {
        holder.name.text = pokemon[position].name.capitalize()
        Picasso.with(context)
            .load(pokemon[position].sprites.frontDefault)
            .into(holder.image)
    }
}