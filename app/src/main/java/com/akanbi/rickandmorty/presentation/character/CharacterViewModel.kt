package com.akanbi.rickandmorty.presentation.character

import androidx.lifecycle.viewModelScope
import com.akanbi.rickandmorty.common.ProviderContext
import com.akanbi.rickandmorty.domain.GetListCharacterUseCase
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.core.CoreViewModel
import com.akanbi.rickandmorty.presentation.core.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val listCharacterUseCase: GetListCharacterUseCase,
    private val providerContext: ProviderContext
) : CoreViewModel<CharacterUIState, CharacterUIEvent>() {
    private val reducer = CharacterReducer(CharacterUIState.initial())
    override val state: StateFlow<CharacterUIState>
        get() = reducer.state
//    private val _characterList = MutableLiveData<FlowState<List<Character>>>()
//    val characterList: LiveData<FlowState<List<Character>>> = _characterList

//    private val _swipeRefresh = MutableLiveData<Boolean>()
//    val swipeRefresh: LiveData<Boolean> = _swipeRefresh

    init {
        viewModelScope.launch(providerContext.main) {
            list()
        }
    }

    fun list() {
        viewModelScope.launch(providerContext.main) {
            listCharacterUseCase.execute(
                onSuccess = {
//                    sendEvent(CharacterUIEvent.OnLoading(isLoading = false))
//                    sendEvent(CharacterUIEvent.OnRefresh(isRefreshing = false))
                    sendEvent(CharacterUIEvent.ShowCharacters(
                        isLoading = false,
                        isRefreshing = false,
                        isPagination = false,
                        characters = it.characterList as MutableList<Character>
                    ))
                },
                onError = {
//                    sendEvent(CharacterUIEvent.OnLoading(isLoading = false))
//                    sendEvent(CharacterUIEvent.OnRefresh(isRefreshing = false))
                    sendEvent(CharacterUIEvent.OnError(isError = true))
                }
            )
        }
    }

//    fun list() {
//        viewModelScope.launch(providerContext.main) {
//            _characterList.postLoading(View.VISIBLE)
//            listCharacterUseCase.execute(
//                onSuccess = {
//                    _characterList.postLoading(View.GONE)
//                    _swipeRefresh.postValue(false)
//                    _characterList.postSuccess(it.characterList)
//                },
//                onError = {
//                    _characterList.postLoading(View.GONE)
//                    _swipeRefresh.postValue(false)
//                    _characterList.postError(it)
//                }
//            )
//        }
//    }

    fun refreshList() {
        listCharacterUseCase.resetPage()
        list()
    }

    private class CharacterReducer(initialState: CharacterUIState) :
        Reducer<CharacterUIState, CharacterUIEvent>(initialState) {
        override fun reduce(oldState: CharacterUIState, event: CharacterUIEvent) {
            when(event) {
                is CharacterUIEvent.OnLoading -> {
                    Timber.d("CharacterList ----> OnLoading")
                    setState(oldState.copy(
                        isLoading = event.isLoading,
                        isError = false,
                        isRefreshing = false
                    ))
                }
                is CharacterUIEvent.OnError -> {
                    Timber.d("CharacterList ----> OnError")
                    setState(oldState.copy(
                        isLoading = false,
                        isError = event.isError
                    ))
                }
                is CharacterUIEvent.ShowCharacters -> {
                    Timber.d("CharacterList ----> ShowCharacters")
                    setState(oldState.copy(
                        isLoading = event.isLoading,
                        isError = false,
                        isRefreshing = event.isRefreshing,
                        isPagination = event.isPagination,
                        characters = event.characters
                    ))
                }
                is CharacterUIEvent.OnPagination -> {
                    Timber.d("CharacterList ----> OnPagination")
                    setState(oldState.copy(
                        isLoading = event.isLoading,
                        isError = false,
                        isRefreshing = event.isRefreshing,
                        isPagination = event.isPagination,
                        characters = event.characters
                    ))
                }
                is CharacterUIEvent.OnRefresh -> {
                    Timber.d("CharacterList ----> OnRefresh")
                    setState(oldState.copy(
                        isLoading = event.isLoading,
                        isError = false,
                        isRefreshing = event.isRefreshing,
                        isPagination = event.isPagination,
                        characters = event.characters
                    ))
                }
            }
        }
    }

    fun sendEvent(event: CharacterUIEvent) {
        reducer.sendEvent(event)
    }

}