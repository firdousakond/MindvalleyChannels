package com.mindvalley.mindvalleyapptest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.mindvalley.mindvalleyapptest.domain.model.CategoryEntity
import com.mindvalley.mindvalleyapptest.ui.theme.Grey500
import com.mindvalley.mindvalleyapptest.ui.theme.Typography
import com.mindvalley.mindvalleyapptest.ui.theme.White
import kotlin.collections.List

@Composable
fun Category(modifier: Modifier = Modifier, category: List<CategoryEntity>) {

    LazyVerticalGrid(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
            .testTag("categoryList")
            .height(
                (LocalConfiguration.current.screenHeightDp/3).dp), columns = GridCells.Fixed(2)
    ) {
        items(count = category.count()) { index ->
            CategoryItem(modifier = modifier, category = category[index])
        }
    }
}

@Composable
fun CategoryItem(modifier: Modifier, category: CategoryEntity) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(35.dp))
            .background(Grey500.copy(alpha = .2f)), contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = modifier,
            text = category.name ?: "",
            style = Typography.h6, color = White
        )
    }
}