package com.akanbi.rickandmorty.presentation.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akanbi.rickandmorty.common.ProviderContext
import com.akanbi.rickandmorty.common.presentation.FlowState
import com.akanbi.rickandmorty.common.presentation.postError
import com.akanbi.rickandmorty.common.presentation.postSuccess
import com.akanbi.rickandmorty.domain.GetListCharacterUseCase
import com.akanbi.rickandmorty.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val listCharacterUseCase: GetListCharacterUseCase,
    private val providerContext: ProviderContext
): ViewModel() {
    private val _characterList = MutableLiveData<FlowState<List<Character>>>()
    val characterList: LiveData<FlowState<List<Character>>> = _characterList

    fun list() {
        viewModelScope.launch(providerContext.main) {
            listCharacterUseCase.execute(
                onSuccess = {
                    _characterList.postSuccess(it)
                },
                onError = {
                    _characterList.postError(it)
                }
            )
        }
    }

}