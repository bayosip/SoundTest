package com.warrantylife.soundtest.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.warrantylife.soundtest.viewmodel.MainViewModel

@Composable
fun SoundTestScreen(viewModel: MainViewModel) {

    val state = viewModel._state
    val frequency = remember {
        mutableStateOf("")
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (frequencyInput, enter, frequencyText, playButton) = createRefs()
        TextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.7f)
                .wrapContentHeight()
                .constrainAs(frequencyInput) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = frequency.value,
            onValueChange = {
                frequency.value = it
            },
            label = {
                Text(
                    text = "Frequency",
                    fontSize = 15.sp
                )
            }
        )

        Button(
            onClick = {
                viewModel.setUserFrequency(inputFrequency = frequency.value.toInt())
            },
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize()
                .constrainAs(enter) {
                    top.linkTo(parent.top)
                    start.linkTo(frequencyInput.end, 8.dp)
                    end.linkTo(parent.end, 16.dp)
                }) {
            Text(
                text = "Enter",
                fontSize = 24.sp,
            )
        }

        if (state.value.inputFrequency >= 0) {
            Text(
                text = "${state.value.inputFrequency} Hz",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(frequencyText) {
                        top.linkTo(frequencyInput.bottom, 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        } else {
            Text(
                text = state.value.msg,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(frequencyText) {
                        top.linkTo(frequencyInput.bottom, 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }

        Button(
            onClick = { viewModel.playTrack() },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(playButton) {
                    top.linkTo(frequencyText.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
            Text(
                text = "Play",
                fontSize = 24.sp,
            )
        }
    }
}