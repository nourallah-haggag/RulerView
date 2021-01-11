package com.rumbl.rumbl_pt.features.profile.list

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.services.ImageLoadingService
import kotlinx.android.synthetic.main.item_gallery_photo.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class TrainerGalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KoinComponent {
    private val imageLoadingService: ImageLoadingService by inject()
    fun bind(item: String) {
        with(itemView)
        {
            imageLoadingService.loadCenterCropImageWithPlaceholder(
                context,
                item,
                ContextCompat.getDrawable(context, R.drawable.ic_rumbl_logo)!!,
                iv_personal_trainer_gallery_image
            )
        }
    }
}