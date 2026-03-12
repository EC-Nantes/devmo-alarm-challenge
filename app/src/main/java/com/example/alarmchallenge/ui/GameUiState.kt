package com.example.alarmchallenge.ui;

/**
 * Data class that represents the game UI state
 */
data class GameUiState(
        val currentTimerBaseSecondsValue: String = "30",
        val currentTimerBaseMinutesValue: String = "0",


        var currentMinutes: String = "30",
        var currentSeconds: String = "0",

        var isTimerRunning: Boolean = true
)

