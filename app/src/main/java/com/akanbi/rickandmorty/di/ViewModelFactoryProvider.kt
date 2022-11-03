package com.akanbi.rickandmorty.di

import com.akanbi.rickandmorty.presentation.character.CharacterDetailsViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {
    fun bookDetailsViewModelFactory(): CharacterDetailsViewModel.Factory
}