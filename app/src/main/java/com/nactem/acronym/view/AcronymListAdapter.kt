package com.nactem.acronym.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nactem.acronym.databinding.AcronymItemBinding
import com.nactem.acronym.model.Lfs

class AcronymListAdapter(private val acronyms: ArrayList<Lfs>):RecyclerView.Adapter<AcronymListAdapter.AcronymViewHolder>() {

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        holder.bind(acronyms.get(position))
    }

    override fun getItemCount() = acronyms.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {
        val binding = AcronymItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcronymViewHolder(binding)
    }

    fun updateAcronymList(newAcronymList: List<Lfs>){
        acronyms.clear()
        acronyms.addAll(newAcronymList)
        notifyDataSetChanged()
    }

    inner class AcronymViewHolder(val binding: AcronymItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(lfs: Lfs){
            binding.lfText.text = lfs.lf
            binding.freq.text = "Frequence: "+lfs.freq.toString()
            binding.since.text = "Since: "+lfs.since.toString()
        }
    }
}