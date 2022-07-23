package com.sadapay.assessment.network

import com.sadapay.assessment.models.GitTrendingReposResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("search/repositories?q=language=+sort:stars")
    suspend fun getTrendingRepos(
        @Query("page") page: Int
    ): GitTrendingReposResponse

}