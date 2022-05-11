package com.example.week8pokemon.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week8pokemon.data.api.PokemonClient
import com.example.week8pokemon.data.api.PokemonInterface
import com.example.week8pokemon.data.repository.NetworkState
import com.example.week8pokemon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: PokemonListViewModel
    private lateinit var newRecyclerView: RecyclerView
    lateinit var pokemonListRepository: PokemonListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService: PokemonInterface = PokemonClient.getClient()
        pokemonListRepository = PokemonListRepository(apiService)
    }
    override fun onResume() {
        super.onResume()
        viewModel = getViewModel(100, 0)
        viewModel.pokemonList.observe(this) {
            val pokemonAdapter = PokemonListAdapter(it.results)
            newRecyclerView = this.binding.myRecyclerView
            newRecyclerView.adapter = pokemonAdapter
            newRecyclerView.layoutManager = GridLayoutManager(this, 2)
        }
        viewModel.networkState.observe(
            this,
            Observer {
                binding.progressBar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
                binding.txtError.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
            }
        )
    }
    private fun getViewModel(limit: Int, offset: Int): PokemonListViewModel {
        return ViewModelProviders.of(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return PokemonListViewModel(pokemonListRepository, limit, offset) as T
                }
            }
        ) [PokemonListViewModel::class.java]
    }
}
