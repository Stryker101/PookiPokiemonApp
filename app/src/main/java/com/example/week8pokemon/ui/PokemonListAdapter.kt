package com.example.week8pokemon.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week8pokemon.R
import com.example.week8pokemon.data.responses.Result
import com.example.week8pokemon.pokemonDetails.PokemonDetailsActivity

class PokemonListAdapter(private val pokeList: List<Result>) : RecyclerView.Adapter<PokemonListAdapter.PokemonItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item_layout, parent, false)
        return PokemonItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
        val current = pokeList[position]
        holder.pokemonName.text = current.name.capitalize()

        holder.itemView.apply {
            Glide.with(this)
                .load(
                    "https://raw.githubusercontent" +
                        ".com/PokeAPI/sprites/master/sprites/pokemon/other" +
                        "/official-artwork/${position + 1}.png"
                ).into(holder.pokemonImage)
        }
        holder.pokemonItems.setOnClickListener {
            val intent = Intent(holder.itemView.context, PokemonDetailsActivity::class.java)
            intent.putExtra("position", position.toString())
            intent.putExtra("name", current.name)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return pokeList.size
    }

    inner class PokemonItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonImage: ImageView = itemView.findViewById(R.id.pokemon_item_ivImage)
        val pokemonName: TextView = itemView.findViewById(R.id.pokemon_item_tvName)
        val pokemonItems: View = itemView.findViewById(R.id.pokemon_itemView)
    }
}
