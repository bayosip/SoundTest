package com.warrantylife.soundtest.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.warrantylife.soundtest.viewmodel.MainViewModel

@Composable
fun SoundTestScreen(viewModel: MainViewModel){

    val state = viewModel._state
    val frequency = remember {
        mutableStateOf("__ hz")
    }
    Box(modifier = Modifier.fillMaxSize()) {

    }
}