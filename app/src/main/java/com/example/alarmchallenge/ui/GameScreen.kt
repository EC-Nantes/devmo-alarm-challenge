package com.example.alarmchallenge.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.ui.unit.sp
import com.example.alarmchallenge.R

@Composable
fun StartGameScreen(
    chronoViewModel: ChronoViewModel = viewModel(),
    navigateToChrono: () -> Unit,
) {
    val gameUiState by chronoViewModel.uiState.collectAsState()
    val mediumPadding = dimensionResource(R.dimen.padding_medium)

    Button(
        modifier = Modifier
            .width(140.dp)
            .height(140.dp),
        onClick = { navigateToChrono() },
        shape = CutCornerShape(16.dp),
    ) {
        Text(
            text = stringResource(R.string.stop),
            fontSize = 20.sp
        )
    }
}
