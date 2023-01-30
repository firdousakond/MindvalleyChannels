package com.mindvalley.mindvalleyapptest.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mindvalley.mindvalleyapptest.R
import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.CategoryEntity
import com.mindvalley.mindvalleyapptest.domain.model.ChannelEntity
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import com.mindvalley.mindvalleyapptest.ui.theme.Typography
import com.mindvalley.mindvalleyapptest.ui.util.ShimmerListItem
import timber.log.Timber

@Composable
fun ChannelScreen(modifier: Modifier = Modifier, viewModel: ChannelViewModel, isLoading: Boolean) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.getChannelsData() }) {

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
}

@Composable
fun SetCategoryList(viewModel: ChannelViewModel) {
    var categoryEntity by rememberSaveable {
        mutableStateOf(listOf<CategoryEntity>())
    }

    val category by viewModel.categoryStateFlow.collectAsStateWithLifecycle()
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
    Category(category = categoryEntity)
}

@Composable
fun SetEpisodeList(viewModel: ChannelViewModel) {
    var mediaEntity by rememberSaveable {
        mutableStateOf(listOf<MediaEntity>())
    }
    val episodes by viewModel.episodeStateFlow.collectAsStateWithLifecycle()
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
    NewEpisodes(episodes = mediaEntity)

}

@Composable
fun SetChannelList(viewModel: ChannelViewModel) {
    var channelEntity by rememberSaveable {
        mutableStateOf(listOf<ChannelEntity>())
    }

    val channels by viewModel.channelStateFlow.collectAsStateWithLifecycle()
    when (channels) {
        is Resource.Loading -> {
            Timber.e("Loading episodes")
        }
        is Resource.Success -> {
            channelEntity = (channels as Resource.Success<List<ChannelEntity>>).data
        }
        is Resource.Error -> {
            Timber.e((channels as Resource.Error).message)
        }
    }
    Channels(channel = channelEntity)
}

@Composable
fun ShowShimmerAnimation(isLoading: Boolean, viewModel: ChannelViewModel) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            repeat(5) {
                ShimmerListItem(
                    isLoading = isLoading,
                    contentAfterLoading = {
                        ChannelScreen(viewModel = viewModel, isLoading = isLoading)
                    },
                    modifier = Modifier
                        .height(350.dp)
                        .padding(10.dp)
                )
            }
        }
}
