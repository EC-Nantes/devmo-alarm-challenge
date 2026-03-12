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

    //var currentSeconds by mutableStateOf("30")
    //    private set
    //var currentMinutes by mutableStateOf("0")
    //    private set

    init {
        relaunchTimer()
    }

    /*
     * Re-initializes the game data to restart the game.
     */
    fun relaunchTimer() {
        //val seconds = futureTimersBaseSecondsValue.toIntOrNull() ?:30
        //val minutes = futureTimersBaseMinutesValue.toIntOrNull() ?:0
        _uiState.value = GameUiState(
            currentTimerBaseSecondsValue = formatTimer(futureTimersBaseSecondsValue),
            currentTimerBaseMinutesValue = formatTimer(futureTimersBaseMinutesValue),
            isTimerRunning = false,
        )
        updateCurrentTime(futureTimersBaseSecondsValue, futureTimersBaseMinutesValue)
    }

    /*
     * Update the user's guess
     */
    fun updateCurrentTime(seconds: String, minutes: String){
        _uiState.value.currentSeconds = formatTimer(seconds)
        _uiState.value.currentMinutes = formatTimer(minutes)

    }

    fun formatTimer(chiffre : String) :String
    {
        return if (chiffre.length == 1) "0$chiffre" else chiffre
    }

    /*
     * Update the user's guess
     */
    fun updateFutureMinutes(minutes: String){
        var minutesInt = minutes.toIntOrNull() ?:0
        if(minutesInt<0) minutesInt = 0
        if(minutesInt>59) minutesInt = 59
        futureTimersBaseMinutesValue = minutesInt.toString()
    }

    /*
     * Update the user's guess
     */
    fun updateFutureSeconds(seconds: String){
        var secondsInt = seconds.toIntOrNull() ?:0
        if(secondsInt<0) secondsInt = 0
        if(secondsInt>59) secondsInt = 59
        futureTimersBaseSecondsValue = secondsInt.toString()
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
