package com.ristu.newsapp

import com.ristu.newsapp.models.NewsItem
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("everything?q=tesla&from=2024-07-16&sortBy=publishedAt&apiKey=dac973bec6164810bec35c79f39afd24")
    suspend fun getAllNews(): Response<NewsItem>
}