package com.example.aplikasiberita.data.repository

import androidx.paging.PagingData
import com.example.aplikasiberita.data.ResultState
import com.example.aplikasiberita.data.model.ArticlesItem
import com.example.aplikasiberita.data.model.ArticlesItemEverything
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getHeadline(country: String, category: String?): Flow<ResultState<List<ArticlesItem>>>
    fun getEverythingPaging(pageNumber: Int, query: String): Flow<PagingData<ArticlesItemEverything>>
}