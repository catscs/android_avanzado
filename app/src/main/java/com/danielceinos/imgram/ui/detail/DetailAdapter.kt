package com.danielceinos.imgram.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielceinos.imgram.data.imgurapi.Image
import com.danielceinos.imgram.databinding.ImageDetailLayoutBinding
import com.danielceinos.imgram.utils.extensions.load

class DetailAdapter : ListAdapter<Image, DetailAdapter.ViewHolder>(VenueDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ImageDetailLayoutBinding = ImageDetailLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = getItem(position)
        holder.bindTo(image)
    }

    class VenueDiffCallback : DiffUtil.ItemCallback<Image>() {

        override fun areItemsTheSame(oldItem: Image, newItem: Image) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Image, newItem: Image) = oldItem == newItem
    }

    inner class ViewHolder(private val binding: ImageDetailLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(image: Image) {
            binding.image.load(image.link)
        }
    }
}
