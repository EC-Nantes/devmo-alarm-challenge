package com.example.alarmchallenge.ui;

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel containing the app data and methods to process the data
 */
class GameViewModel : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var futureTimersBaseSecondsValue by mutableStateOf("30")
        private set
    var futureTimersBaseMinutesValue by mutableStateOf("0")
        private set

    var currentSeconds by mutableStateOf("30")
        private set
    var currentMinutes by mutableStateOf("0")
        private set

    init {
        relaunchTimer()
    }

    /*
     * Re-initializes the game data to restart the game.
     */
    fun relaunchTimer() {
        val seconds = futureTimersBaseSecondsValue.toIntOrNull() ?:30
        val minutes = futureTimersBaseMinutesValue.toIntOrNull() ?:0
        _uiState.value = GameUiState(
            currentTimerBaseSecondsValue = seconds,
            currentTimerBaseMinutesValue = minutes,
            isTimerRunning = false,
        )
        updateCurrentTime(futureTimersBaseSecondsValue, futureTimersBaseMinutesValue)
    }

    /*
     * Update the user's guess
     */
    fun updateCurrentTime(seconds: String, minutes: String){
        currentSeconds = seconds
        currentMinutes = minutes
    }

    /*
     * goes to the next second
     */
    fun advanceTime() {
        if (_uiState.value.isTimerRunning) {
            var seconds = futureTimersBaseSecondsValue.toIntOrNull() ?:0
            var minutes = futureTimersBaseMinutesValue.toIntOrNull() ?:0

            if(seconds!=0)
            {
                seconds--;
            }
            else if(minutes!=0)
            {
                minutes--;
                seconds=59;
            }

            // Reset user guess
            updateCurrentTime(seconds.toString(), minutes.toString())

            if(seconds==0 && minutes == 0)
            {
                _uiState.value.isTimerRunning=false;
            }
        }

    }

}
