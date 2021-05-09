package com.oliversanz.marvel.android.herolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oliversanz.marvel.BR
import com.oliversanz.marvel.databinding.ListItemHeroBinding
import com.oliversanz.marvel.domain.model.HeroListModel

class HeroListAdapter : RecyclerView.Adapter<HeroListAdapter.HeroListViewHolder>() {

    private var data: List<HeroListModel> = emptyList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroListViewHolder {
        val binding = ListItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroListViewHolder, position: Int) {
        holder.binding.setVariable(BR.hero, data[holder.adapterPosition])
    }

    fun updateData(newData: List<HeroListModel>){
        this.data = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size
    override fun getItemId(position: Int): Long = data[position].id.hashCode().toLong()

    inner class HeroListViewHolder(val binding: ListItemHeroBinding): RecyclerView.ViewHolder(binding.root)

}