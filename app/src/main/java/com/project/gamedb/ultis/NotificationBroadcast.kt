package com.project.gamedb.ultis

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.project.gamedb.service.NotificationService

class NotificationBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        NotificationService.enqueueWork(context, intent)
    }
}
