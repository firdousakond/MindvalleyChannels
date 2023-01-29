package com.mindvalley.mindvalleyapptest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.CategoryEntity
import com.mindvalley.mindvalleyapptest.domain.model.ChannelEntity
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import com.mindvalley.mindvalleyapptest.domain.usecase.GetCategoriesUseCase
import com.mindvalley.mindvalleyapptest.domain.usecase.GetChannelUseCase
import com.mindvalley.mindvalleyapptest.domain.usecase.GetNewEpisodeUseCase
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import timber.log.Timber

class ChannelViewModel(
    private val getChannelUseCase: GetChannelUseCase,
    private val getNewEpisodeUseCase: GetNewEpisodeUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _episodeStateFlow = MutableStateFlow<Resource<List<MediaEntity>>>(Resource.Loading)
    val episodeStateFlow = _episodeStateFlow.asStateFlow()

    private val _channelStateFlow =
        MutableStateFlow<Resource<List<ChannelEntity>>>(Resource.Loading)
    val channelStateFlow = _channelStateFlow.asStateFlow()

    private val _categoryStateFlow =
        MutableStateFlow<Resource<List<CategoryEntity>>>(Resource.Loading)
    val categoryStateFlow = _categoryStateFlow.asStateFlow()

    fun getChannelsData() {
        viewModelScope.launch {
            supervisorScope {
                //get new episodes
                launch {
                    getNewEpisodeUseCase.getNewEpisodes()
                        .catch {
                            Timber.e(it.message)
                            _episodeStateFlow.value = Resource.Error(it.message ?: "")
                        }.collect {
                            _episodeStateFlow.value = it
                        }
                }
                //get channels
                launch {
                    getChannelUseCase.getChannels()
                        .catch {
                            Timber.e(it.message)
                            _channelStateFlow.value = Resource.Error(it.message ?: "")
                        }.collect {
                            _channelStateFlow.value = it
                        }
                }
                launch {
                    //Get categories
                    getCategoriesUseCase.getCategories()
                        .catch {
                            Timber.e(it.message)
                            _categoryStateFlow.value = Resource.Error(it.message ?: "")
                        }.collect {
                            _categoryStateFlow.value = it
                        }
                }
            }
        }
    }
}