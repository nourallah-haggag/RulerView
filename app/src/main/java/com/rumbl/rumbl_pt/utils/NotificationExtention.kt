package com.rumbl.rumbl_pt.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.core.app.NotificationCompat
import com.rumbl.rumbl_pt.BuildConfig
import com.rumbl.rumbl_pt.Constants
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.home.MainActivity


private const val NOTIFICATION_CHANNEL_ID = BuildConfig.APPLICATION_ID + ".channel"
fun sendNotification(
    context: Context,
    notificationTitle: String?,
    notificationContent: String?,
    bundle: Bundle? = null
) {
    val notificationManager = context
        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        && notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null
    ) {
        val name = context.getString(R.string.app_name)
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            name,
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.createNotificationChannel(channel)
    }
    val resultIntent = Intent(context, MainActivity::class.java)
    val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
        resultIntent.putExtra(Constants.SESSION_RESPONSE_NOTIFICATION_KEY, bundle)
        addNextIntentWithParentStack(resultIntent)
        getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }


    val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_rumbl_logo)
        .setColorized(true)
        .setContentIntent(resultPendingIntent)
        .setContentTitle(notificationTitle)
        .setContentText(notificationContent)
        .setAutoCancel(true)
        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
        .build()

    notificationManager.notify(getUniqueId(), notification)
}


private fun getUniqueId() = ((System.currentTimeMillis() % 10000).toInt())