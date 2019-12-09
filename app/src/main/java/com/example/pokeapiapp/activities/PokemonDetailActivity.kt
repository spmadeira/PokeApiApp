package com.example.pokeapiapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.example.pokeapiapp.R
import com.example.pokeapiapp.data.IPokeDetail
import com.example.pokeapiapp.data.PokemonData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies

class PokemonDetailActivity : AppCompatActivity(), IPokeDetail {

    fun FavoriteClick(){
        if (PokemonData.IsFavorited(pokeId!!)){
            PokemonData.RemoveFavorite(pokeId!!)
            pokemon_detail_favorite_button.setImageResource(android.R.drawable.btn_star_big_off)
        } else {
            PokemonData.AddFavorite(pokeId!!)
            pokemon_detail_favorite_button.setImageResource(android.R.drawable.btn_star_big_on)
        }
    }

    override fun UpdateDetail(species: PokemonSpecies){
        runOnUiThread{
            var info = String()
            species.evolvesFromSpecies?.let {
                info += "Evolui de: ${it.name.capitalize()}\n"
            }
            val flavor = species.flavorTextEntries.find { it.language.name == "en" }
            flavor?.let{
                info += "Flavor text: ${it.flavorText}\n"
            }
            val generation = species.generation.id
            info += "Geração: ${generation}\n"
            info += "Abilidades:\n"
            pokemon.abilities.forEach{ability ->
                info += "${ability.ability.name.capitalize()}\n"
            }
            info += "Altura: ${pokemon.height}\nPeso: ${pokemon.weight}\nStats:\n"
            pokemon.stats.forEach{stat ->
                info += "${stat.stat.name} - ${stat.baseStat}\n"
            }

            pokemon_detail_info.text = info
        }
    }

    private lateinit var pokemon: Pokemon
    private var pokeId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        pokeId = intent.getIntExtra( "pokemon_id",0)
        pokemon = PokemonData.pokemon[pokeId!!]


        pokemon_detail_name.text = pokemon.name.capitalize()
        Picasso.with(this)
            .load(pokemon.sprites.frontDefault)
            .into(pokemon_detail_image)

        PokemonData.LoadInfo(pokeId!!+1,this)

        pokemon_detail_share.setOnClickListener { Share() }
        pokemon_detail_info.movementMethod = ScrollingMovementMethod()

        if (PokemonData.IsFavorited(pokeId!!)){
            pokemon_detail_favorite_button.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            pokemon_detail_favorite_button.setImageResource(android.R.drawable.btn_star_big_off)
        }

        pokemon_detail_favorite_button.setOnClickListener {
            FavoriteClick()
        }
    }

    fun Share(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "${pokemon.name.capitalize()} ${pokemon_detail_info.text}")
        }
        startActivity(Intent.createChooser(sendIntent, "Compartilhar Pokemon"))
    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
    }

}
