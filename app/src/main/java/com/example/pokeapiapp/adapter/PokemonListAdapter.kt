package com.example.pokeapiapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiapp.R
import com.example.pokeapiapp.data.PokemonData
import com.squareup.picasso.Picasso
import me.sargunvohra.lib.pokekotlin.model.Pokemon

class PokemonListAdapter (val context: Context, val listener: PokemonClickListener): RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>(){

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    //private val pokemonList: MutableList<Pokemon> = mutableListOf()

    fun UpdateList(){
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false)
        return PokemonViewHolder(view, context, listener)
    }

    override fun getItemCount(): Int {
        return PokemonData.pokemon.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.name.text = PokemonData.pokemon[position].name.capitalize()
        Picasso.with(context)
            .load(PokemonData.pokemon[position].sprites.frontDefault)
            .into(holder.image)
    }

    class PokemonViewHolder(view: View, context: Context, listener: PokemonClickListener): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.pokemon_listitem_image)
        val name: TextView = view.findViewById(R.id.pokemon_listitem_name)

        init {
            image.setOnClickListener{
                listener.onDetailClick(it,adapterPosition)
            }
            name.setOnClickListener{
                listener.onDetailClick(it, adapterPosition)
            }
        }
    }
}