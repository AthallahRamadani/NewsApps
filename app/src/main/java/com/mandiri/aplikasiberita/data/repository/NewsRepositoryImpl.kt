package com.mandiri.aplikasiberita.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mandiri.aplikasiberita.data.ResultState
import com.mandiri.aplikasiberita.data.datasource.remote.api.service.ApiService
import com.mandiri.aplikasiberita.data.model.ArticlesItem
import com.mandiri.aplikasiberita.data.model.ArticlesItemEverything
import com.mandiri.aplikasiberita.data.paging.NewsPagingSource
import com.mandiri.aplikasiberita.data.utils.toEverythingPaging
import com.mandiri.aplikasiberita.data.utils.toHeadline
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val apiService: ApiService
) : NewsRepository {
    override fun getHeadline(country: String, category: String?): Flow<ResultState<List<ArticlesItem>>> = flow {
        emit(ResultState.Loading)
        try {
            val response = if (category != null) {
                apiService.getTopHeadlines(country,category)
            } else {
                apiService.getTopHeadlines(country)
            }
            val data = response.body()?.toHeadline()
            if (data != null) {
                emit(ResultState.Success(data))
            }
        } catch (e: Exception){
            emit(ResultState.Error(e))
        }
    }

    override fun getEverythingPaging(pageNumber: Int, query: String): Flow<PagingData<ArticlesItemEverything>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 1, initialLoadSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(apiService, query)
            },
        ).flow.map {
            it.map { articlesItemEverything ->
                articlesItemEverything.toEverythingPaging()
            }
        }
    }
}