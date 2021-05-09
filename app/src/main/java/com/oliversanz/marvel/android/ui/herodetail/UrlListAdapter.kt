package com.oliversanz.marvel.android.ui.herodetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oliversanz.marvel.BR
import com.oliversanz.marvel.databinding.ListItemUrlBinding
import com.oliversanz.marvel.domain.model.UrlModel

class UrlListAdapter(
    private val clickHandler: HeroDetailActivity.UrlClickHandler
) : RecyclerView.Adapter<UrlListAdapter.UrlListViewHolder>() {

    private var data: List<UrlModel> = emptyList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UrlListViewHolder {
        val binding = ListItemUrlBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UrlListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UrlListViewHolder, position: Int) {
        holder.binding.setVariable(BR.url, data[holder.adapterPosition])
        holder.binding.setVariable(BR.clickHandler, clickHandler)
    }

    fun updateData(newData: List<UrlModel>){
        this.data = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size
    override fun getItemId(position: Int): Long = data[position].id.hashCode().toLong()

    inner class UrlListViewHolder(val binding: ListItemUrlBinding): RecyclerView.ViewHolder(binding.root)

}