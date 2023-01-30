package com.mindvalley.mindvalleyapptest.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mindvalley.mindvalleyapptest.R
import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.CategoryEntity
import com.mindvalley.mindvalleyapptest.domain.model.ChannelEntity
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import com.mindvalley.mindvalleyapptest.ui.theme.Typography
import timber.log.Timber

@Composable
fun ChannelScreen(modifier: Modifier = Modifier, viewModel: ChannelViewModel) {

    Column(
        modifier = modifier
            .padding(top = 50.dp, bottom = 20.dp)
    ) {

        Text(
            modifier = modifier.padding(start = 20.dp, end = 20.dp),
            text = stringResource(id = R.string.label_channel),
            style = Typography.h4
        )

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                modifier = modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                text = stringResource(id = R.string.label_new_episodes),
                style = Typography.h6,
            )
            SetEpisodeList(viewModel)
            SetChannelList(viewModel)
            Text(
                modifier = modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                text = stringResource(id = R.string.label_browse_categories),
                style = Typography.h6,
            )
            SetCategoryList(viewModel)
        }
    }

}

@Composable
fun SetCategoryList(viewModel: ChannelViewModel) {
    var categoryEntity: List<CategoryEntity>? = ArrayList()
    val category by viewModel.categoryStateFlow.collectAsState(initial = Resource.Loading)
    when (category) {
        is Resource.Loading -> {
            Timber.e("Loading episodes")
        }
        is Resource.Success -> {
            categoryEntity = (category as Resource.Success<List<CategoryEntity>>).data
        }
        is Resource.Error -> {
            Timber.e((category as Resource.Error).message)
        }
    }
    categoryEntity?.let { Category(category = it) }
}

@Composable
fun SetEpisodeList(viewModel: ChannelViewModel) {
    var mediaEntity: List<MediaEntity>? = ArrayList()
    val episodes by viewModel.episodeStateFlow.collectAsState(initial = Resource.Loading)
    when (episodes) {
        is Resource.Loading -> {
            Timber.e("Loading episodes")
        }
        is Resource.Success -> {
            mediaEntity = (episodes as Resource.Success<List<MediaEntity>>).data
        }
        is Resource.Error -> {
            Timber.e((episodes as Resource.Error).message)
        }
    }
    mediaEntity?.let { NewEpisodes(episodes = it) }

}

@Composable
fun SetChannelList(viewModel: ChannelViewModel) {
    var channelEntity: List<ChannelEntity>? = ArrayList()
    val episodes by viewModel.channelStateFlow.collectAsState(initial = Resource.Loading)
    when (episodes) {
        is Resource.Loading -> {
            Timber.e("Loading episodes")
        }
        is Resource.Success -> {
            channelEntity = (episodes as Resource.Success<List<ChannelEntity>>).data
        }
        is Resource.Error -> {
            Timber.e((episodes as Resource.Error).message)
        }
    }
    channelEntity?.let { Channels(channel = it) }
}
