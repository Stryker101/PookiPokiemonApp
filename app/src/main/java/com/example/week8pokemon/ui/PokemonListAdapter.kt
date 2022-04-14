package com.example.week8pokemon.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week8pokemon.Extensions
import com.example.week8pokemon.R
import com.example.week8pokemon.data.repository.NetworkState
import com.example.week8pokemon.data.responses.Result
import com.example.week8pokemon.pokemonDetails.PokemonDetailsActivity

class PokemonListAdapter(private val pokeList: List<Result>): RecyclerView.Adapter<PokemonListAdapter.PokemonItemViewHolder>() {
//     val POKEMON_VIEW_TYPE = 1
//     val NETWORK_VIEW_TYPE = 2
//    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item_layout, parent, false)
        return PokemonItemViewHolder(layout)

    }

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
        val current = pokeList[position]
        holder.pokemonName.text = current.name.capitalize()

        holder.itemView.apply {
            Glide.with(this)
                .load("https://raw.githubusercontent" +
                        ".com/PokeAPI/sprites/master/sprites/pokemon/other" +
                        "/official-artwork/${position + 1}.png").into(holder.pokemonImage)
//                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${current.id}.png").into(holder.pokemonImage)
        }
        holder.pokemonItems.setOnClickListener {
            val intent = Intent(holder.itemView.context,PokemonDetailsActivity::class.java)
            intent.putExtra("position", position.toString())
            intent.putExtra("name",current.name)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

//    private fun hasExtraRow(): Boolean {
//        return networkState != null && networkState != NetworkState.LOADED
//    }

    override fun getItemCount(): Int {
        return pokeList.size
    }

//    override fun getItemViewType(position: Int): Int {
//        return if (hasExtraRow() && position == itemCount - 1) {
//            NETWORK_VIEW_TYPE
//        } else {
//            POKEMON_VIEW_TYPE
//        }
//    }

//    class PokemonDiffCallback: DiffUtil.ItemCallback<Result>() {
//        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
//            return oldItem.url == newItem.url
//        }
//
//        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
//            return oldItem == newItem
//        }
//    }



   inner class PokemonItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       val pokemonImage: ImageView = itemView.findViewById(R.id.pokemon_item_ivImage)
       val pokemonName : TextView = itemView.findViewById(R.id.pokemon_item_tvName)
       val pokemonItems : View = itemView.findViewById(R.id.pokemon_itemView)
    }
//    class NetworkStateItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        fun bind(networkState: NetworkState?){
//            if (networkState != null && networkState == NetworkState.LOADING){
//                itemView.findViewById<View>(R.id.progress_bar_item).visibility = View.VISIBLE
//            }
//            else{
//                itemView.findViewById<View>(R.id.progress_bar_item).visibility = View.GONE
//            }
//            if (networkState != null && networkState == NetworkState.ERROR) {
//                itemView.findViewById<TextView>(R.id.error_messge_item).visibility = View.VISIBLE
//                itemView.findViewById<TextView>(R.id.error_messge_item).text = networkState.msg
//            } else if (networkState != null && networkState == NetworkState.ENDOFLIST) {
//                itemView.findViewById<TextView>(R.id.error_messge_item).visibility = View.VISIBLE
//                itemView.findViewById<TextView>(R.id.error_messge_item).text = networkState.msg
//            }
//            else{
//                itemView.findViewById<TextView>(R.id.error_messge_item).visibility = View.GONE
//            }
//        }
//    }

//    fun setNetworkState(newNetworkState: NetworkState){
//        val previousState: NetworkState? = this.networkState
//        val hadExtraRow: Boolean = hasExtraRow()
//        this.networkState = newNetworkState
//        val hasExtraRow: Boolean = hasExtraRow()
//
//        if(hadExtraRow != hasExtraRow){
//            if (hadExtraRow){
//                notifyItemRemoved(super.getItemCount())
//            }else {
//                notifyItemInserted(super.getItemCount())
//            }
//        }else if (hasExtraRow && previousState != newNetworkState) {
//            notifyItemChanged(itemCount - 1)
//        }
//
//    }
//    fun bind(result: Result?,context: Context){
//        itemView.findViewById<TextView>(R.id.pokemon_item_tvName).text = result?.name!!.capitalize()
//        itemView.findViewById<View>(R.id.pokemon_itemView).apply {
//            Glide.with(this)
//                .load("https://raw.githubusercontent" +
//                        ".com/PokeAPI/sprites/master/sprites/pokemon/other" +
//                        "/official-artwork/${position + 1}.png").into(itemView.findViewById<ImageView>(R.id.pokemon_item_ivImage))
//        }
//        itemView.setOnClickListener {
//            val intent = Intent(context, PokemonDetailsActivity::class.java)
//            intent.putExtra("position", position.toString())
//            intent.putExtra("name",result.name)
//            context.startActivity(intent)
//        }
//    }
}