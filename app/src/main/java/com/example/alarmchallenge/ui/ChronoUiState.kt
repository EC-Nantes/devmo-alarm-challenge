package com.example.alarmchallenge.ui;

/**
 * Data class that represents the game UI state
 */
data class ChronoUiState(
        val currentTimerBaseSecondsValue: String = "30",
        val currentTimerBaseMinutesValue: String = "0",


        val currentMinutes: String = "30",
        val currentSeconds: String = "0",

        val isTimerRunning: Boolean = true
)