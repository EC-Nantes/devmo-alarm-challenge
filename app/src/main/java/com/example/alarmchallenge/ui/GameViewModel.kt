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
class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

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
        )
        updateUserGuess("")
    }

    fun updateUserGuess(guessedWord: String) {
        userGuess = guessedWord
    }

    fun checkUserGuess() {
        if (userGuess.trim().equals(currentCapital, ignoreCase = true)) {
            val updatedScore = _uiState.value.score + 1
            updateGameState(updatedScore)
        } else {
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
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
        } else {
            val nextCountry = pickNewCountryAndCapital()
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = nextCountry,
                    currentWordCount = currentState.currentWordCount.inc(),
                    score = updatedScore
                )
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