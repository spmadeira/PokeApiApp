package com.example.pokeapiapp.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.Pokemon

class PokemonData {
    companion object {
        val pokemon: MutableList<Pokemon> = mutableListOf()
        var pokeList: IPokeList? = null
        var favorites: String? = ""
        private lateinit var sharedPreferences: SharedPreferences

        fun StartLoadAllData(context: Context){
            val client = PokeApiClient()

            sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
            favorites = sharedPreferences.getString("favorites", "")

            val background = object : Thread(){
                override fun run(){
                    (1..807).iterator().forEach { index ->
                        val p = client.getPokemon(index)
                        pokemon.add(p)

                        Log.i("api", "${p.name} carregado.")

                        pokeList?.let {pL ->
                            pL.UpdateList()
                        }
                    }
                }
            }

            background.start()
        }

        fun LoadInfo(index: Int, detail: IPokeDetail){
            val client = PokeApiClient()

            val background = object : Thread(){
                override fun run() {
                    val species = client.getPokemonSpecies(index)

                    detail.UpdateDetail(species)
                }
            }

            background.start()
        }

        fun IsFavorited(index: Int): Boolean{
            val pFavorites = favorites?.split(';')

            val found = pFavorites?.any { it.toIntOrNull() == index }

            return found!!
        }

        fun GetFavorites() : List<Int> {
            val list = mutableListOf<Int>()
            val pFavorites = favorites?.split(';')

            pFavorites?.forEach{
                it.toIntOrNull()?.let { it1 -> list.add(it1) }
            }

            return list
        }

        fun AddFavorite(index: Int){
            favorites += "${index};"
            sharedPreferences.edit().putString("favorites", favorites).apply()
        }

        fun RemoveFavorite(index: Int){
            val pFavorites = favorites?.split(';')
            val newFavorites = pFavorites!!.toMutableList()

            newFavorites.removeAll{
                fav -> fav.toIntOrNull() == index
            }

            var newString = String()
            newFavorites.forEach{
                newString += "${it};"
            }
            favorites = newString
            sharedPreferences.edit().putString("favorites",newString).apply()
        }
    }
}