package com.example.alarmchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alarmchallenge.R
import com.example.alarmchallenge.ui.theme.AlarmChallengeTheme

@Composable
fun GameScreen(gameViewModel: GameViewModel = viewModel()) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    val mediumPadding = dimensionResource(R.dimen.padding_medium)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.app_name),
            style = typography.titleLarge,
            fontSize = 30.sp,
        )

        // Adding a Spacer of height 20dp
        Spacer(modifier = Modifier.height(80.dp))

        //Chrono displayed at the top
        Text(
            text =
                gameUiState.currentTimerBaseMinutesValue.toString() + " : "
                    + gameUiState.currentTimerBaseSecondsValue.toString(),
            style = typography.titleLarge,
            fontSize = 35.sp,
        )

        Spacer(modifier = Modifier.height(100.dp))

        ButtonLayout(
            onClickStop = { gameViewModel.relaunchTimer() },
            onClickRelaunch = { gameViewModel.relaunchTimer() },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(mediumPadding)
        )

        Spacer(modifier = Modifier.height(40.dp))

        TimeLayout(
            userMinutes = gameViewModel.futureTimersBaseMinutesValue,
            userSeconds = gameViewModel.futureTimersBaseSecondsValue,
            updateMinutes = {gameViewModel.relaunchTimer()},
            updateSeconds = {gameViewModel.relaunchTimer()},
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(mediumPadding)
        )
    }
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
                .width(140.dp)
                .height(140.dp),
            onClick = { onClickStop() },
            shape = CutCornerShape(16.dp),
        ) {
            Text(
                text = stringResource(R.string.stop),
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.width(50.dp))
        OutlinedButton(
            modifier = Modifier
                .width(140.dp)
                .height(140.dp),
            onClick = { onClickRelaunch() },
            shape = CircleShape,
        ) {
            Text(
                text = stringResource(R.string.relaunch),
                fontSize = 20.sp
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
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.minutes),
            style = typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f)
        )
        OutlinedTextField(
            value = userMinutes,
            singleLine = true,
            shape = shapes.small,
            modifier = Modifier.weight(0.25f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),
            onValueChange = updateMinutes,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
        )
        Text(
            text = stringResource(R.string.seconds),
            style = typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f),
        )
        OutlinedTextField(
            value = userSeconds,
            singleLine = true,
            shape = shapes.small,
            modifier = Modifier.weight(0.25f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),
            onValueChange = updateSeconds,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
        )
    }
}
