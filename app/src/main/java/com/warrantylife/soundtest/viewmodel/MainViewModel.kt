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

    private val TAG = javaClass.canonicalName
    private val state: MutableState<ScreenState> = mutableStateOf(ScreenState())
    val _state: State<ScreenState> = state


    fun setUserFrequency(inputFrequency: Int) {
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
    }
    fun playTrack() {
        // start playing audio and release resources when done
        _state.value.audio?.play()
        Thread.sleep(Constants.durationMs.toLong())
        _state.value.audio?.release()
    }
}