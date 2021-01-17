package com.rumbl.rumbl_pt.features.home.home_list

import android.graphics.Paint
import android.view.View
import androidx.core.content.ContextCompat
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.services.ImageLoadingService
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.utils.Dateutils
import kotlinx.android.synthetic.main.item_session.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class SessionViewHolder(itemView: View) : HomeItemsViewHolder(itemView), KoinComponent {
    private val imageLoadingService: ImageLoadingService by inject()
    override fun <T> bind(item: T, homeItemsInteractionListener: HomeItemsInteractionListener?) {
        if (item is SessionsResponse) {
            with(itemView)
            {
                setOnClickListener {
                    homeItemsInteractionListener?.onSessionItemClicked(item)
                }
                when (item.session_type) {
                    1 -> {
                        tv_session_type.text = context.getString(R.string.training_session)
                    }
                    2 -> {
                        tv_session_type.text = context.getString(R.string.consultation_session)
                    }
                }
                item.customer.avatar?.let { image ->
                    imageLoadingService.loadCenterCropImageWithPlaceholder(
                        context,
                        image,
                        ContextCompat.getDrawable(context, R.drawable.ic_rumbl_logo)!!,
                        iv_trainer
                    )
                }
                tv_session_date.text = Dateutils.formatToLongDate(item.date)
                tv_session_time.text = item.time_slot.from
                tv_trainer_name.text = item.customer.name
                tv_session_location.apply {
                    text = item.customer_address
                    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
                }
            }
        }
    }
}