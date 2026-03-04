package com.example.alarmchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alarmchallenge.R
import com.example.alarmchallenge.ui.theme.AlarmChallengeTheme

@Composable
fun ChronoLayout(gameViewModel: GameViewModel = viewModel()
) {

}

@Composable
fun ButtonLayout(
    onClickStop : () -> Unit,
    onClickRelaunch : () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row() {
        Button(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            onClick = { onClickStop() },
            shape = CutCornerShape(16.dp),
        ) {
            Text(
                text = stringResource(R.string.stop),
                fontSize = 24.sp
            )
        }

        OutlinedButton(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            onClick = { onClickRelaunch() },
            shape = CircleShape,
        ) {
            Text(
                text = stringResource(R.string.relaunch),
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun TimeLayout(
    userMinutes: String,
    userSeconds: String,
    updateMinutes : (String) -> Unit,
    updateSeconds : (String) -> Unit,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier,
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
            text = stringResource(R.string.seconds),
            style = typography.titleMedium,
            color = colorScheme.onPrimary
        )
        OutlinedTextField(
            value = userSeconds,
            singleLine = true,
            shape = shapes.small,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),
            onValueChange = updateSeconds,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone() }
            )
        )
    }
}