package com.mindvalley.mindvalleyapptest.domain.usecase

import com.mindvalley.mindvalleyapptest.domain.Resource
import com.mindvalley.mindvalleyapptest.domain.model.CategoryEntity
import com.mindvalley.mindvalleyapptest.domain.repository.IChannelRepo
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase (private val iChannelRepo: IChannelRepo) : IGetCategoriesUseCase{
    override suspend fun getCategories(): Flow<Resource<List<CategoryEntity>>> = iChannelRepo.getCategories()
}