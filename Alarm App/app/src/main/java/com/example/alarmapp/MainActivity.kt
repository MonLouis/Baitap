package com.example.alarmapp

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.icu.util.Calendar
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TimePicker
import android.content.Context


class MainActivity : AppCompatActivity() {
    private lateinit var alarmManager:AlarmManager
    private lateinit var timePicker:TimePicker
    private lateinit var editText: EditText
    private lateinit var checkBox: CheckBox
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var pendingIntent: PendingIntent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarmManager =getSystemService(ALARM_SERVICE) as AlarmManager
        timePicker = findViewById(R.id.timePicker)
        editText = findViewById(R.id.editText)
        checkBox = findViewById(R.id.checkBox)
        mediaPlayer = MediaPlayer.create(this,R.raw.soundalarm)


        val amButton = timePicker.findViewById<Button>(Resources.getSystem().getIdentifier("am_label", "id", "android"))
        val pmButton = timePicker.findViewById<Button>(Resources.getSystem().getIdentifier("pm_label", "id", "android"))

// Thêm OnClickListener cho nút "AM"
        amButton.setOnClickListener {
            // Khi nút "AM" được chạm vào, đổi màu chữ của nó thành màu xanh lam và đặt màu chữ của nút "PM" thành màu xám
            amButton.setTextColor(Color.CYAN)
            pmButton.setTextColor(Color.GRAY)
        }

// Thêm OnClickListener cho nút "PM"
        pmButton.setOnClickListener {
            // Khi nút "PM" được chạm vào, đổi màu chữ của nó thành màu xanh lam và đặt màu chữ của nút "AM" thành màu xám
            pmButton.setTextColor(Color.CYAN)
            amButton.setTextColor(Color.GRAY)
        }
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            setAlarm()
        }


        checkBox.setOnCheckedChangeListener{_,_ ->
            if(checkBox.isChecked ){
                setAlarm()
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setAlarm() {
        val calendar=Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,timePicker.hour)
        calendar.set(Calendar.MINUTE,timePicker.minute)
        calendar.set(Calendar.SECOND,0)


        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        val intent= Intent(this,AlarmReceiver::class.java)
        intent.putExtra("isRepeating", checkBox.isChecked)

        // Tạo PendingIntent để gửi Intent khi báo thức kích hoạt
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Đặt báo thức bằng AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        // Hiển thị thời gian đã chọn trong EditText
        editText.setText("${timePicker.hour}:${timePicker.minute}")
    }
}



