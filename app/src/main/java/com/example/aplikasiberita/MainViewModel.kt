package com.example.aplikasiberita

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.aplikasiberita.data.ResultState
import com.example.aplikasiberita.data.model.ArticlesItem
import com.example.aplikasiberita.data.model.ArticlesItemEverything
import com.example.aplikasiberita.data.repository.NewsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _headlineState = MutableStateFlow<ResultState<List<ArticlesItem>>>(ResultState.Loading)
    val headlineState: StateFlow<ResultState<List<ArticlesItem>?>> = _headlineState

    private var country = "us"
    var category : String? = null
    private var pageNumber = 1
    var searchQuery = "Indonesia"

    fun getHeadline() {
        viewModelScope.launch {
            newsRepository.getHeadline(country, category).collect {
                _headlineState.value = it
                delay(500)
            }
        }
    }


    fun getEverythingPagingData(): Flow<PagingData<ArticlesItemEverything>> {
        return newsRepository.getEverythingPaging(pageNumber, searchQuery)
    }
}