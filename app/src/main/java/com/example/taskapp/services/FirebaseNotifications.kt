package com.example.taskapp.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.taskapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseNotifications : FirebaseMessagingService(){

    override fun onMessageReceived(message: RemoteMessage) {
        Log.e("ololo", "onMessageReceived:"+ message.notification?.title)
        Log.e("ololo", "onMessageReceived:"+ message.notification?.                                                   body)
        senNotifications(message)
        super.onMessageReceived(message)
    }

    private fun senNotifications(remoteMessage: RemoteMessage){
        val notificationsBuilder = NotificationCompat.Builder(this,"task_channelId")
        notificationsBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
        notificationsBuilder.setContentTitle(remoteMessage.notification?.title)
        notificationsBuilder.setContentTitle(remoteMessage.notification?.body)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager

        val channel = NotificationChannel("task_channelId",
            "Chanel human readable title ",NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(1,notificationsBuilder.build())
    }
}