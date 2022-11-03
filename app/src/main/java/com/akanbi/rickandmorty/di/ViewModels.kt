package com.akanbi.rickandmorty.di

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.character.CharacterDetailsViewModel
import dagger.hilt.android.EntryPointAccessors

@Composable
fun characterDetailsViewModel(
    viewModelStoreOwner: ViewModelStoreOwner,
    character: Character,
): CharacterDetailsViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).bookDetailsViewModelFactory()

    return viewModel(
        viewModelStoreOwner = viewModelStoreOwner,
        factory = CharacterDetailsViewModel.provideFactory(factory, character)
    )
}