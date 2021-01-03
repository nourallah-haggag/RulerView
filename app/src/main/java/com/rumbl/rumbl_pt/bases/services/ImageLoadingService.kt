package com.rumbl.rumbl_pt.bases.services

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView

/**
 * Created by Mohamed Shalan on 4/20/20.
 */

interface ImageLoadingService {

    fun loadCenterCropImageWithPlaceholder(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		view: ImageView
	)

    fun loadFitCenterCropImageWithPlaceholder(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		view: ImageView
	)

    fun loadImageWithPlaceholder(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		view: ImageView
	)

    fun loadImageWithPlaceholderCenterCropRoundedCorner(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		radius: Int,
		view: ImageView
	)

    fun loadCircleImageWithPlaceholder(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		view: ImageView
	)

    fun loadCircleImageWithPlaceholderFromFile(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		view: ImageView
	)

    fun loadCircleImageWithPlaceholderFromUri(
		context: Context,
		imageUrl: Uri,
		placeholder: Drawable,
		view: ImageView
	)

    fun loadImageWithPlaceholderAndTopCorners(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		radius: Float,
		view: ImageView
	)

    fun loadImageWithPlaceHolderStartTopBottomRadius(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		startTopRadius: Float,
		startBottomRadius: Float,
		view: ImageView
	)
}
