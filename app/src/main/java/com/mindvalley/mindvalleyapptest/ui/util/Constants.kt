package com.mindvalley.mindvalleyapptest.ui.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mindvalley.mindvalleyapptest.ui.theme.Shapes

val imageModifierPortrait = Modifier
    .size(width = 152.dp, height = 228.dp)
    .clip(Shapes.large)

val imageModifierLandscape = Modifier
    .size(width = 320.dp, height = 172.dp)
    .clip(Shapes.medium)

val iconModifier = Modifier
    .size(width = 50.dp, height = 50.dp)

val dividerModifier = Modifier
    .fillMaxWidth()
    .width(1.dp)
    .padding(top = 30.dp, start = 10.dp, end = 10.dp)

