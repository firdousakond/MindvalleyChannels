package com.mindvalley.mindvalleyapptest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.mindvalley.mindvalleyapptest.ui.theme.MindValleyChannelTheme
import com.mindvalley.mindvalleyapptest.ui.theme.appBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindValleyChannelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = appBackground
                ) {
                    ChannelScreen()
                }
            }
        }
    }
}
