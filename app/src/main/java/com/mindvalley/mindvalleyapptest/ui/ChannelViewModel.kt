package com.mindvalley.mindvalleyapptest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import com.mindvalley.mindvalleyapptest.domain.usecase.GetCategoriesUseCase
import com.mindvalley.mindvalleyapptest.domain.usecase.GetChannelUseCase
import com.mindvalley.mindvalleyapptest.domain.usecase.GetNewEpisodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber

class ChannelViewModel(
    private val getChannelUseCase: GetChannelUseCase,
    private val getNewEpisodeUseCase: GetNewEpisodeUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _episodeStateFlow = MutableStateFlow<Resource<List<MediaEntity>>>(Resource.Loading)
    val episodeStateFlow = _episodeStateFlow.asStateFlow()

    fun getNewEpisodes() {
        viewModelScope.launch {
            getNewEpisodeUseCase.getNewEpisodes()
                .catch {
                    Timber.e(it.message)
                    _episodeStateFlow.value = Resource.Error(it.message ?: "")
                }.collect {
                    _episodeStateFlow.value = it
                }
        }
    }
}