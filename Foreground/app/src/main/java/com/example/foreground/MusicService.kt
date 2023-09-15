package com.example.foreground

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MusicService : Service() {
    private lateinit var mediaPlayer: MediaPlayer
    private val channelId = "music_channel"
    private val notificationId = 1

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.sena)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val stopIntent = Intent(this, MusicService::class.java)
        stopIntent.action = Constants.STOP_ACTION
        val stopPendingIntent = PendingIntent.getService(
            this,
            0,
            stopIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Music Service")
            .setContentText("Playing music")
            .setSmallIcon(R.drawable.sena)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.se_na, "Stop", stopPendingIntent)
            .build()

        startForeground(notificationId, notification)

        mediaPlayer.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    object Constants {
        const val STOP_ACTION = "com.example.foreground.STOP"
    }
}
