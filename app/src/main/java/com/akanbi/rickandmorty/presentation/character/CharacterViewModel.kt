package com.akanbi.rickandmorty.presentation.character

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akanbi.rickandmorty.common.ProviderContext
import com.akanbi.rickandmorty.common.presentation.FlowState
import com.akanbi.rickandmorty.common.presentation.postError
import com.akanbi.rickandmorty.common.presentation.postLoading
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

    private val _swipeRefresh = MutableLiveData<Boolean>()
    val swipeRefresh: LiveData<Boolean> = _swipeRefresh

    fun list() {
        viewModelScope.launch(providerContext.main) {
            _characterList.postLoading(View.VISIBLE)
            listCharacterUseCase.execute(
                onSuccess = {
                    _characterList.postLoading(View.GONE)
                    _swipeRefresh.postValue(false)
                    _characterList.postSuccess(it.characterList)
                },
                onError = {
                    _characterList.postLoading(View.GONE)
                    _swipeRefresh.postValue(false)
                    _characterList.postError(it)
                }
            )
        }
    }

    fun refreshList() {
        listCharacterUseCase.resetPage()
        list()
    }

}