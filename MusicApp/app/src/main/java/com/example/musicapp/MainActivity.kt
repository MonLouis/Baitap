package com.example.musicapp


import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.button)

        startButton.setOnClickListener {
            sendNotificationMedia()
        }
    }

    private fun sendNotificationMedia() {
        val mediaSessionCompat = MediaSessionCompat(this, "tag")
        mediaSessionCompat.setMediaButtonReceiver(null)
        val channelId = "my_channel"
        if (checkSelfPermission(android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            val notification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_adjust_24)
                .setSubText("Mon Louis")
                .setContentText("title of song")
                .setContentText("Singer")
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.sena))
                .addAction(R.drawable.skip_previous_24, "Previous", null)
                .addAction(R.drawable.play_arrow_24, "Pause", null)
                .addAction(R.drawable.skip_next_24, "Next", null)
                .addAction(R.drawable.pause_circle_filled_24, "Pause", null)
                .setStyle(
                    androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1 /* #1: pause button \*/)
                        .setMediaSession(mediaSessionCompat.sessionToken)
                )
                .build()
            val notificationManagerCompat = NotificationManagerCompat.from(this)
            notificationManagerCompat.notify(1, notification)
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.INTERNET), 1)
        }
    }
}













