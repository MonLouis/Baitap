
package com.example.alarmapp

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onReceive(context: Context?, intent: Intent?) {


        if (context != null) {
            // Kiểm tra xem báo thức có lặp lại hay không
            val isRepeating = intent?.getBooleanExtra("isRepeating", false) ?: false
            // Kiểm tra quyền và yêu cầu nếu cần
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.RECEIVE_BOOT_COMPLETED
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    123,
                    Intent(context, AlarmReceiver::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.RECEIVE_BOOT_COMPLETED),
                    123
                )
            } else {
                // Tạo thông báo và phát âm thanh chuông
                val builder = buildNotification(context, isRepeating)
                val notification = builder.build()

                val mediaPlayer =
                    MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI)
                mediaPlayer.start()
            }
        }
    }


    private fun buildNotification(context: Context, isRepeating: Boolean): NotificationCompat.Builder {
        val channelId = "alarm_channel_id"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Đảm bảo rằng kênh thông báo đã được định nghĩa
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Tên Kênh",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Mô tả Kênh"
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background) // Icon nhỏ cho thông báo
            .setContentTitle("Báo thức") // Tiêu đề của thông báo
            .setContentText("Đã đến thời gian thức dậy") // Nội dung của thông báo
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Ưu tiên của thông báo
            .setCategory(NotificationCompat.CATEGORY_ALARM) // Loại thông báo

        if (isRepeating) {
            // Nếu báo thức lặp lại, đặt thông báo ở trạng thái "ongoing" để người dùng không thể tắt nó dễ dàng
            notificationBuilder.setOngoing(true)
        }

        // Đối tượng Intent và PendingIntent cho việc tắt báo thức
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Thêm action để tắt báo thức khi người dùng nhấn vào
        notificationBuilder.addAction(
            R.drawable.ic_launcher_foreground, // Icon cho action tắt
            "Tắt", // Tên action
            pendingIntent // PendingIntent để xử lý khi action được nhấn
        )

        return notificationBuilder
    }
}



