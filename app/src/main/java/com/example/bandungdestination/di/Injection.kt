package com.example.bandungdestination.di

import com.example.bandungdestination.data.BandungDestinationRepository

object Injection {
    fun provideRepository(): BandungDestinationRepository{
        return BandungDestinationRepository.getInstance()
    }
}