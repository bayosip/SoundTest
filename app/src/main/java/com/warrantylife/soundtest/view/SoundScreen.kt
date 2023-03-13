package com.warrantylife.soundtest.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.warrantylife.soundtest.viewmodel.MainViewModel

@Composable
fun SoundTestScreen(viewModel: MainViewModel){

    val state = viewModel._state
    val frequency = remember {
        mutableStateOf("__ hz")
    }
    viewModel.setUserFrequency()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (frequencyInput, enter, frequencyText, playButton)= createRefs()
        TextField(
            value = frequency.value,
            onValueChange = {
                frequency.value = it
            },
            label = { Text("Label") }
        )
        Text(text = "${frequency.value} Hz")

        Button(
            onClick = {viewModel.playTrack()},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(playButton) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }) {
            Text(text = "Play")
        }

        Button(
            onClick = {viewModel.playTrack()},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(playButton) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }) {
            Text(text = "Play")
        }
    }
}