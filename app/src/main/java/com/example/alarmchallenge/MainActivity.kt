package com.example.alarmchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.postDelayed

import com.example.alarmchallenge.ui.theme.AlarmChallengeTheme
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {

    lateinit var mainHandler: Handler


    private val updateTextTask = object : Runnable {
        override fun run() {
            minusOneSecond()

            mainHandler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AlarmChallengeTheme {
                AlarmChallengeApp()
            }
        }

        mainHandler = Handler(Looper.getMainLooper())
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateTextTask)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateTextTask)
    }

    fun minusOneSecond() {
    }


}