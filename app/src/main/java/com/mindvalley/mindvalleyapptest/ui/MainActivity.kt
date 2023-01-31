package com.mindvalley.mindvalleyapptest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mindvalley.mindvalleyapptest.ui.theme.MindValleyChannelTheme
import com.mindvalley.mindvalleyapptest.ui.theme.appBackground
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindValleyChannelTheme {
                val viewModel = viewModel<ChannelViewModel>().value
                viewModel.getChannelsData()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = appBackground
                ) {
                    MainChannelScreen(viewModel = viewModel)
                }
            }
        }
    }

    @Composable
    fun MainChannelScreen(viewModel: ChannelViewModel) {
        val isLoading by viewModel.isRefreshing.collectAsStateWithLifecycle()
        ShowShimmerAnimation(isLoading = isLoading, viewModel)
    }

}
