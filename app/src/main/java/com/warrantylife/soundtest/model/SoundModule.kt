package com.warrantylife.soundtest.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SoundModule {

    @Singleton
    @Provides
    fun provideSoundGenerator() = SoundGenerator()
}