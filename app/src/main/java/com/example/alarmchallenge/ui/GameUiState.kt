package com.example.alarmchallenge.ui;

/**
 * Data class that represents the game UI state
 */
data class GameUiState(
        val currentTimerBaseSecondsValue: Int = 30,
        val currentTimerBaseMinutesValue: Int = 0,
        //val futureTimersBaseSecondsValue: Int = 30,
        //val futureTimersBaseMinutesValue: Int = 0,
        var isTimerRunning: Boolean = true
)

