package com.warrantylife.soundtest.model

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import com.warrantylife.soundtest.Constants.numSamples
import com.warrantylife.soundtest.Constants.sampleRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SoundGenerator {

    private lateinit var audioTrack: AudioTrack

    private fun setupAudioAttribute(): AudioAttributes {
        return AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
    }

    private fun setupAudioFormat(): AudioFormat {
        return AudioFormat.Builder()
            .setSampleRate(sampleRate)
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
            .build()
    }

    fun createAndConfigAudioAtInputFrequency(frequency: Int): Flow<AudioTrack> = flow {
        createAudioTrack()
        val samples = DoubleArray(numSamples)

        // generate audio samples for the specified frequency
        for (i in 0 until numSamples) {
            samples[i] = Math.sin(2.0 * Math.PI * i.toDouble() * frequency.toDouble() / sampleRate)
        }
        // write audio samples to AudioTrack buffer
        val buffer = ShortArray(numSamples)
        for (i in 0 until numSamples) {
            val sample = (samples[i] * Short.MAX_VALUE).toInt().toShort()
            buffer[i] = sample
        }
        audioTrack.write(buffer, 0, numSamples)
        emit(audioTrack)
    }

    private fun createAudioTrack() {
        // create and configure AudioTrack object
        audioTrack = AudioTrack(
            setupAudioAttribute(),
            setupAudioFormat(),
            numSamples * 2,
            AudioTrack.MODE_STATIC,
            0
        )
    }
}