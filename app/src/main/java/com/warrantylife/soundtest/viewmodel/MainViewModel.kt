package com.warrantylife.soundtest.viewmodel

import android.media.AudioTrack
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantylife.soundtest.Constants
import com.warrantylife.soundtest.usecases.GenerateSoundUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val generateSoundUseCase: GenerateSoundUseCase
) : ViewModel() {

    private val state: MutableState<ScreenState> = mutableStateOf(ScreenState())
    val _state: State<ScreenState> = state

    fun setUserFrequency(inputFrequency: Int) {
        if (inputFrequency in 0..24000) {
            viewModelScope.launch(Dispatchers.Default) {
                generateSoundUseCase(inputFrequency).collectLatest { audio: AudioTrack ->
                    withContext(Dispatchers.Main) {
                        state.value = _state.value.copy(
                            inputFrequency = inputFrequency,
                            audio = audio,
                        )
                    }
                }
            }
        } else state.value = _state.value.copy(
            msg = "Error! 0-24000 hz only!",
            inputFrequency = -1
        )
    }

    fun playTrack() {
        // start playing audio and release resources when done
        if (_state.value.audio?.state != AudioTrack.STATE_UNINITIALIZED) {
            _state.value.audio?.play()
            Thread.sleep(Constants.durationMs.toLong())
            _state.value.audio?.release()
        } else {
            setUserFrequency(_state.value.inputFrequency)
            if (_state.value.audio?.state == AudioTrack.STATE_INITIALIZED)
                playTrack()
        }
    }
}