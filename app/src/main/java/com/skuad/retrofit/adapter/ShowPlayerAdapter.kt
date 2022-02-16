package com.skuad.retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skuad.retrofit.databinding.ItemPlayerShowBinding
import com.skuad.retrofit.model.Employee
import com.skuad.retrofit.model.Player
import vh.ShowPlayerViewHolder

class ShowPlayerAdapter(private val onItemClick: (Player) -> Unit,
                        private val onDeleteClick: (Player) -> Unit) :
    RecyclerView.Adapter<ShowPlayerViewHolder>() {

    private val playerList: MutableList<Player> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowPlayerViewHolder {
        val binding =
            ItemPlayerShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowPlayerViewHolder(binding,onItemClick = onItemClick,
        onDeleteClick = onDeleteClick)
    }

    override fun onBindViewHolder(holder: ShowPlayerViewHolder, position: Int) {
        val player = playerList[position]
        holder.bindData(player)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    fun submitList(player: List<Player>) {
        playerList.clear()
        playerList.addAll(player)
        notifyDataSetChanged()
    }
}
