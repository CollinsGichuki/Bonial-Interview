package com.snilloc.bonialinterview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.snilloc.bonialinterview.databinding.BrochureItemBinding
import com.snilloc.bonialinterview.domain.model.BrochureData

class BrochureAdapter :
    ListAdapter<BrochureData, BrochureAdapter.BrochureAdapterViewHolder>(BrochureComparator()) {

    class BrochureAdapterViewHolder(private val binding: BrochureItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(brochure: BrochureData) {
            binding.apply {
                //Load the image with Glide
                Glide.with(itemView)
                    .load(brochure.brochureImage)//Regular Photo url
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_error_image)
                    .error(R.drawable.ic_error_image)
                    .into(brochureImage)

                //Bind the retailer's name
                retailerNameTv.text = brochure.retailerName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrochureAdapterViewHolder {
        //Create the view
        val binding =
            BrochureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrochureAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrochureAdapterViewHolder, position: Int) {
        val currentBrochure = getItem(position)
        //If the current item isn't null, bind the data to the view
        if (currentBrochure != null) {
            holder.binding(currentBrochure)
        }
    }

    class BrochureComparator : DiffUtil.ItemCallback<BrochureData>() {
        override fun areItemsTheSame(oldItem: BrochureData, newItem: BrochureData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BrochureData, newItem: BrochureData): Boolean {
            return oldItem.brochureImage == newItem.brochureImage
        }
    }
}