package com.warrantylife.soundtest.viewmodel

import android.media.AudioTrack

data class ScreenState(
    val inputFrequency: Int? =0,
    val audio:AudioTrack? = null,
)