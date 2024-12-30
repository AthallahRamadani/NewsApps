package com.example.aplikasiberita.data.utils

import com.example.aplikasiberita.data.datasource.remote.api.response.EverythingResponse
import com.example.aplikasiberita.data.datasource.remote.api.response.HeadlineResponse
import com.example.aplikasiberita.data.datasource.remote.api.response.Source
import com.example.aplikasiberita.data.datasource.remote.api.response.SourceEverything
import com.example.aplikasiberita.data.model.ArticlesItem
import com.example.aplikasiberita.data.model.ArticlesItemEverything

fun HeadlineResponse.toHeadline(): List<ArticlesItem> {
    return this.articles?.map {article ->
        ArticlesItem(
            article.source?.toSource() ?: ArticlesItem.Source("", ""),
            article.author ?: "",
            article.title ?: "",
            article.description ?: "",
            article.url ?: "",
            article.urlToImage ?: "",
            article.publishedAt ?: "",
            article.content ?: ""
        )
    } ?: emptyList()
}


private fun Source.toSource(): ArticlesItem.Source =
    ArticlesItem.Source(
        this.id ?: "",
        this.name ?: ""
    )

fun EverythingResponse.toEverything(): List<ArticlesItemEverything> {
    return this.articles?.map {article ->
        ArticlesItemEverything(
            article.source?.toSource() ?: ArticlesItemEverything.Source("", ""),
            article.author ?: "",
            article.title ?: "",
            article.description ?: "",
            article.url ?: "",
            article.urlToImage ?: "",
            article.publishedAt ?: "",
            article.content ?: ""
        )
    } ?: emptyList()
}

private fun SourceEverything.toSource(): ArticlesItemEverything.Source =
    ArticlesItemEverything.Source(
        this.id ?: "",
        this.name ?: ""
    )

fun com.example.aplikasiberita.data.datasource.remote.api.response.ArticlesItemEverything.toEverythingPaging(): ArticlesItemEverything {
    return ArticlesItemEverything(
        source = ArticlesItemEverything.Source(id = this.source?.id, name = this.source?.name ?: ""),
        author = this.author,
        title = this.title ?: "",
        description = this.description,
        url = this.url ?: "",
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt ?: "",
        content = this.content
    )
}