package com.example.background

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun startService (view: View){
        val intent = Intent(this, MusicService::class.java)
        startService(intent)
    }
    fun stopService (view: View){
        val intent = Intent(this, MusicService::class.java)
        stopService(intent)
    }



    }

