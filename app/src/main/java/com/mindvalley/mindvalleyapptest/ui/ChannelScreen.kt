package com.mindvalley.mindvalleyapptest.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mindvalley.mindvalleyapptest.R
import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.MediaEntity
import com.mindvalley.mindvalleyapptest.ui.theme.Typography
import timber.log.Timber

@Composable
fun ChannelScreen(modifier: Modifier = Modifier, viewModel: ChannelViewModel){
    Column( modifier = modifier.padding( top = 50.dp, bottom = 20.dp)) {
      Text(
          modifier = modifier.padding(start = 20.dp, end = 20.dp),
          text = stringResource(id = R.string.label_channel),
          style = Typography.h4
      )
        Text(
            modifier = modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
            text = stringResource(id = R.string.label_new_episodes),
            style = Typography.h6,
        )
        var mediaEntity : List<MediaEntity>? = ArrayList()
        val episodes by viewModel.episodeStateFlow.collectAsState(initial = Resource.Loading)
        when(episodes){
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
        if (mediaEntity != null) {
            NewEpisodes(episodes = mediaEntity)
        }
    }
}