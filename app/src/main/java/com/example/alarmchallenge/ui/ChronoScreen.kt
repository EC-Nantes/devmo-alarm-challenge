package com.example.alarmchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alarmchallenge.R

@Composable
fun ChronoLayout(gameViewModel: GameViewModel = viewModel()
) {

}

@Composable
fun ButtonLayout(
) {

}

@Composable
fun TimeLayout(
    userMinutes: String,
    updateMinutes : (String) -> Unit,
) {
    Row() {
        Text(
            text = stringResource(R.string.minutes),
            style = typography.titleMedium,
            color = colorScheme.onPrimary
        )
        OutlinedTextField(
            value = userMinutes,
            singleLine = true,
            shape = shapes.small,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),
            onValueChange = updateMinutes,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone() }
            )
        )
        Text(
            text = stringResource(R.string.secondes),
            style = typography.titleMedium,
            color = colorScheme.onPrimary
        )
    }
}