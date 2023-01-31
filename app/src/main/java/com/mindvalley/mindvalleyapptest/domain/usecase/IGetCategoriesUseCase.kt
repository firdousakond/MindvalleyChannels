package com.mindvalley.mindvalleyapptest.domain.usecase

import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface IGetCategoriesUseCase {
    suspend fun getCategories(): Flow<Resource<List<CategoryEntity>>>
}