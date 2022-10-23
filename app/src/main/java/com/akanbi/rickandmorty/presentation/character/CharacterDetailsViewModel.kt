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
import com.akanbi.rickandmorty.domain.GetListEpisodeByCharacterUseCase
import com.akanbi.rickandmorty.domain.core.ParametersDTO
import com.akanbi.rickandmorty.presentation.components.model.SimpleElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val useCase: GetListEpisodeByCharacterUseCase,
    private val providerContext: ProviderContext
) : ViewModel() {
    private val _episodesList = MutableLiveData<FlowState<List<SimpleElement>>>()
    val episodesList: LiveData<FlowState<List<SimpleElement>>> = _episodesList

    fun listEpisodesBy(ids: List<String>) {
        viewModelScope.launch(providerContext.main) {
            _episodesList.postLoading(View.VISIBLE)
            useCase.execute(
                parameters = ParametersDTO {
                    add("ids", ids)
                },
                onSuccess = {
                    _episodesList.postLoading(View.GONE)
                    _episodesList.postSuccess(it)
                },
                onError = {
                    _episodesList.postLoading(View.GONE)
                    _episodesList.postError(it.code)
                }
            )
        }
    }
}