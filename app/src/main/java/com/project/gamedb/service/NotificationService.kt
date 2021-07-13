package com.project.gamedb.service

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.project.gamedb.R
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.data.source.remote.OnDataLoadedCallback
import com.project.gamedb.ui.main.MainActivity
import com.project.gamedb.ultis.Repositories
import java.lang.Exception
import kotlin.random.Random

class NotificationService : JobIntentService() {
    private val id = 1

    fun createNotification(game: String) {
        val notificationManager = NotificationManagerCompat.from(this)
        val intent = MainActivity.getIntent(applicationContext)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(this, id.toString())
            .setSmallIcon(R.drawable.ic_main)
            .setContentTitle(getString(R.string.text_new_notify))
            .setContentText(getString(R.string.text_lets_play,game))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_EMAIL)
        notificationManager.notify(0, notification.build())
    }

    override fun onHandleWork(intent: Intent) {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        repository.getGames(object : OnDataLoadedCallback<ResultGames> {
            override fun onSuccess(data: ResultGames) {
                createNotification(data.results[Random.nextInt(data.results.size)].gameName)
            }

            override fun onFailure(exception: Exception?) {}
        })
    }

    companion object {
        var isAppRunning = false

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, NotificationService::class.java, 0, intent)
        }
    }
}
