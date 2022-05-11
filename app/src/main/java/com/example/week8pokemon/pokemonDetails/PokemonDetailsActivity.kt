package com.example.week8pokemon.pokemonDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.week8pokemon.data.api.PokemonClient
import com.example.week8pokemon.data.api.PokemonInterface
import com.example.week8pokemon.data.repository.NetworkState
import com.example.week8pokemon.data.responses.PokemonDetails
import com.example.week8pokemon.databinding.ActivityPokemonDetailsBinding

class PokemonDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonDetailsBinding
    private lateinit var viewModel: PokemonDetailsViewModel
    private lateinit var pokemonRepository: PokemonDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonName = intent.getStringExtra("name")
        val apiService: PokemonInterface = PokemonClient.getClient()
        pokemonRepository = PokemonDetailsRepository(apiService)

        viewModel = getViewModel(pokemonName!!)
        viewModel.pokemonDetails.observe(
            this,
            Observer {
                bindUI(it)
            }
        )
        viewModel.networkState.observe(
            this,
            Observer {
                binding.progressBar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
                binding.txtError.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
            }
        )
    }

    private fun bindUI(it: PokemonDetails) {
        binding.pokemonName.text = it.name.capitalize()
        binding.pokemonHeightValue.text = "${it.height} M"
        binding.pokemonWeightValue.text = "${it.weight} kg"
        var stats = arrayListOf<Int>()
        for (stat in it.stats) {
            stats.add(stat.baseStat)
        }
        binding.healthBar.progress = stats[0]
        binding.attackBar.progress = stats[1]
        binding.defenseBar.progress = stats[2]
        binding.specialAttckBar.progress = stats[3]
        binding.specialDefenseBar.progress = stats[4]
        binding.speedBar.progress = stats[5]

        Glide.with(this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${it.id}.png").into(binding.pokemonImage)

        var abilities = arrayListOf<String>()
        for (i in it.abilities) {
            abilities.add(i.ability.name)
        }
        binding.Ability1.text = abilities[0]
        binding.Ability2.text = abilities[1]
    }

    private fun getViewModel(pokemonName: String): PokemonDetailsViewModel {
        return ViewModelProviders.of(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return PokemonDetailsViewModel(pokemonRepository, pokemonName) as T
                }
            }
        )[PokemonDetailsViewModel::class.java]
    }
}
