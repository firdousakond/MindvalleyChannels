package com.mindvalley.mindvalleyapptest.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mindvalley.mindvalleyapptest.R
import com.mindvalley.mindvalleyapptest.ui.theme.Typography

@Composable
fun ChannelScreen(modifier: Modifier = Modifier){
    Column(  modifier = modifier.padding(start = 20.dp, top = 50.dp, end = 20.dp, bottom = 20.dp)) {
      Text(
          text = stringResource(id = R.string.label_channel),
          style = Typography.h4
      )
        Text(
            modifier = modifier.padding(top = 30.dp),
            text = stringResource(id = R.string.label_new_episodes),
            style = Typography.h6,
        )
    }
}