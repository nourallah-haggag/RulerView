package com.rumbl.rumbl_pt.features.notifications.firebase_notifications

import android.util.Log
import androidx.core.os.bundleOf
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.rumbl.rumbl_pt.Constants
import com.rumbl.rumbl_pt.bases.services.SerializationService
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.utils.sendNotification
import org.koin.core.KoinComponent
import org.koin.core.inject

class RumblPtNotification : FirebaseMessagingService(), KoinComponent {

    private val serializationService: SerializationService by inject()
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val stringBuilder = StringBuilder()
        //TODO: remove when notification feature is finalized
        p0.data.keys.forEach {
            stringBuilder.append("-$it")
        }
        Log.i("sessionKeys", stringBuilder.toString())
        val session = serializationService.deserialize(
            p0.data[Constants.NOTIFICATION_SESSION_KEY].toString(),
            SessionsResponse::class.java
        )
        val title = p0.data[Constants.NOTIFICATION_TITLE__KEY].toString()
        val content = p0.data[Constants.NOTIFICATION_CONTENT_KEY].toString()
        val bundle = bundleOf()
        session?.let { session ->
            bundle.putParcelable(Constants.SESSION_RESPONSE_NOTIFICATION_KEY, session)
        }
        sendNotification(
            context = this,
            notificationTitle = title,
            notificationContent = content,
            bundle = bundle
        )
    }
}