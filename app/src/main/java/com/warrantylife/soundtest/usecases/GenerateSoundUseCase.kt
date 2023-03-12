package com.warrantylife.soundtest.usecases

import android.media.AudioTrack
import com.warrantylife.soundtest.model.SoundGenerator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateSoundUseCase @Inject constructor(
    private val generator: SoundGenerator
) {
    suspend operator fun invoke(frequency: Int): Flow<AudioTrack> =
        generator.createAndConfigAudioAtInputFrequency(frequency)
}