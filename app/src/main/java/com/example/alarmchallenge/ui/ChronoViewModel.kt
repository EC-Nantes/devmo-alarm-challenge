/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.alarmchallenge.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import com.example.alarmchallenge.data.MAX_SCORE
import com.example.alarmchallenge.data.allCountry

/**
 * ViewModel containing the app data and methods to process the data
 */
class ChronoViewModel : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow(ChronoUiState())
    val uiState: StateFlow<ChronoUiState> = _uiState.asStateFlow()

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

    // Game UI state
    private val _uiState = MutableStateFlow(ChronoUiState())
    val uiState: StateFlow<ChronoUiState> = _uiState.asStateFlow()

    var userGuess by mutableStateOf("")
        private set

    private var usedCountries: MutableSet<String> = mutableSetOf()

    private lateinit var currentCountry: String
    private lateinit var currentCapital: String

    init {
        resetGame()
    }

    fun resetGame() {
        usedCountries.clear()
        val firstCountry = pickNewCountryAndCapital()
        _uiState.value = GameUiState(
            currentScrambledWord = firstCountry,
            currentWordCount = 1,
            score = 0,
            isGameOver = false,
            isGuessedWordWrong = false

    /*
     * Re-initializes the game data to restart the game.
     */
    fun relaunchTimer() {
        //val seconds = futureTimersBaseSecondsValue.toIntOrNull() ?:30
        //val minutes = futureTimersBaseMinutesValue.toIntOrNull() ?:0
        _uiState.value = ChronoUiState(
            currentTimerBaseSecondsValue = formatTimer(futureTimersBaseSecondsValue),
            currentTimerBaseMinutesValue = formatTimer(futureTimersBaseMinutesValue),
            isTimerRunning = true,
        )
        updateUserGuess("")
    }


    /*
     * Update the user's guess
     */
    fun updateCurrentTime(seconds: String, minutes: String){
        _uiState.update { currentState ->
            currentState.copy(
                currentSeconds = formatTimer(seconds),
                currentMinutes = formatTimer(minutes)
            )
        }

    }

    fun timer1Sec() {
        val seconds = _uiState.value.currentSeconds.toIntOrNull() ?: 0
        val minutes = _uiState.value.currentMinutes.toIntOrNull() ?: 0

        var secondsTotal = seconds + minutes * 60

        if (secondsTotal > 0) {
            secondsTotal -= 1
        }
        if (secondsTotal <= 0){
            // Arrête la boucle LaunchedEffect quand on atteint 0
            _uiState.update { it.copy(isTimerRunning = false) }
        }

        val newSeconds = secondsTotal % 60
        val newMinutes = secondsTotal / 60

        updateCurrentTime(newSeconds.toString(), newMinutes.toString())
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
        }
        updateUserGuess("")
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }

    private fun updateGameState(updatedScore: Int) {
        if (updatedScore >= MAX_SCORE) {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
            // Reset user guess
            updateCurrentTime(seconds.toString(), minutes.toString())

            if(seconds==0 && minutes == 0)
            {
                _uiState.update { currentState ->
                    currentState.copy(
                        isTimerRunning = false,
                    )
                }
            }
        }
    }

    private fun pickNewCountryAndCapital(): String {
        val randomCountry = allCountry.keys.random()

        return if (usedCountries.contains(randomCountry)) {
            pickNewCountryAndCapital()
        } else {
            usedCountries.add(randomCountry)
            currentCountry = randomCountry
            currentCapital = allCountry[randomCountry] ?: ""
            currentCountry
        }
    }
}
