package com.warrantylife.soundtest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import com.warrantylife.soundtest.R
import com.warrantylife.soundtest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            Scaffold {
                Box(modifier = Modifier.fillMaxSize()
                    .padding(it.calculateBottomPadding())) {
                    SoundTestScreen(viewModel)
                }
            }
        }
    }
}