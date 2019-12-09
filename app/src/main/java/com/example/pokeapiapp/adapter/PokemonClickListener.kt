package com.example.pokeapiapp.adapter

import android.view.View

interface PokemonClickListener {
    fun onDetailClick(view: View, index: Int)
    fun onFavoriteClick(view: View, index: Int)
}