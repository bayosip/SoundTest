package com.warrantylife.soundtest.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel: ViewModel()  {

    private val TAG = javaClass.canonicalName
    private val state: MutableState<ScreenState> = mutableStateOf(ScreenState())
    val _state: State<ScreenState> = state


    fun setUserFrequency(input:Int){
        viewModelScope.launch (Dispatchers.Default){

        }
    }
}