package com.example.demo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo.R
import com.example.demo.databinding.ItemImagesBinding
import com.example.demo.model.PicturesModel

class ImagesGridAdapter(
    private val items: List<PicturesModel>,
    private val clickListener: (View, Int, PicturesModel) -> Unit,
) :
    RecyclerView.Adapter<ImagesGridAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagesGridAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImagesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ItemImagesBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: PicturesModel) = with(itemView)
        {
            binding.apply {
                Glide.with(itemView).load(item.url).placeholder(R.drawable.placeholder_image).into(imageView)
            }
        }

        override fun onClick(view: View?) {
            view?.let {
                clickListener(
                    it,
                    adapterPosition,
                    items[adapterPosition]
                )
            }
        }

    }
}
