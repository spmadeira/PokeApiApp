package com.example.pokeapiapp.data

import me.sargunvohra.lib.pokekotlin.model.EvolutionChain
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies

interface IPokeDetail {
    fun UpdateDetail(species: PokemonSpecies)
}