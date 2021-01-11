package com.rumbl.rumbl_pt.features.profile.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rumbl.rumbl_pt.R

class TrainerGalleryAdapter(private val trainerGalleryImages: List<String>) :
    RecyclerView.Adapter<TrainerGalleryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainerGalleryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_photo, parent, false)
        return TrainerGalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainerGalleryViewHolder, position: Int) {
        holder.bind(trainerGalleryImages[position])
    }

    override fun getItemCount(): Int = trainerGalleryImages.size
}