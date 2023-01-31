package com.mindvalley.mindvalleyapptest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.CategoryEntity
import com.mindvalley.mindvalleyapptest.domain.model.ChannelEntity
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import com.mindvalley.mindvalleyapptest.domain.usecase.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import timber.log.Timber

class ChannelViewModel(
    private val getChannelUseCase: IGetChannelUseCase,
    private val getNewEpisodeUseCase: IGetNewEpisodesUseCase,
    private val getCategoriesUseCase: IGetCategoriesUseCase
) : ViewModel() {

    private val _episodeStateFlow = MutableStateFlow<Resource<List<MediaEntity>>>(Resource.Loading)
    val episodeStateFlow = _episodeStateFlow.asStateFlow()

    private val _channelStateFlow =
        MutableStateFlow<Resource<List<ChannelEntity>>>(Resource.Loading)
    val channelStateFlow = _channelStateFlow.asStateFlow()

    private val _categoryStateFlow =
        MutableStateFlow<Resource<List<CategoryEntity>>>(Resource.Loading)
    val categoryStateFlow = _categoryStateFlow.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    var apiCallCount = 0

    fun getChannelsData() {
        apiCallCount = 0
        _isRefreshing.value = true
        viewModelScope.launch {
            supervisorScope {
                //get new episodes
                launch {
                    getNewEpisodeUseCase.getNewEpisodes()
                        .catch {
                            Timber.e(it.message)
                            _episodeStateFlow.value = Resource.Error(it.message ?: "")
                            updateRefreshState(false)
                        }.collect {
                            _episodeStateFlow.value = it
                            updateRefreshState(it is Resource.Loading)
                        }
                }
                //get channels
                launch {
                    getChannelUseCase.getChannels()
                        .catch {
                            Timber.e(it.message)
                            _channelStateFlow.value = Resource.Error(it.message ?: "")
                            updateRefreshState(false)
                        }.collect {
                            _channelStateFlow.value = it
                            updateRefreshState(it is Resource.Loading)
                        }
                }
                launch {
                    //Get categories
                    getCategoriesUseCase.getCategories()
                        .catch {
                            Timber.e(it.message)
                            _categoryStateFlow.value = Resource.Error(it.message ?: "")
                            updateRefreshState(false)
                        }.collect {
                            _categoryStateFlow.value = it
                            updateRefreshState(it is Resource.Loading)
                        }
                }
            }
        }
    }

    private fun updateRefreshState(isLoading: Boolean) {
        if (isLoading.not()) {
            apiCallCount++
            if (apiCallCount == 3) {
                _isRefreshing.value = false
                apiCallCount = 0
            }
        }
    }
}