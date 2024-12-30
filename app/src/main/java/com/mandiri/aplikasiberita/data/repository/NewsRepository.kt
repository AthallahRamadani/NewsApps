package com.mandiri.aplikasiberita.data.repository

import androidx.paging.PagingData
import com.mandiri.aplikasiberita.data.ResultState
import com.mandiri.aplikasiberita.data.model.ArticlesItem
import com.mandiri.aplikasiberita.data.model.ArticlesItemEverything
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getHeadline(country: String, category: String?): Flow<ResultState<List<ArticlesItem>>>
    fun getEverythingPaging(pageNumber: Int, query: String): Flow<PagingData<ArticlesItemEverything>>
}