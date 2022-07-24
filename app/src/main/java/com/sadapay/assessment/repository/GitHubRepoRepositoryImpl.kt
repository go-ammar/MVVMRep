package com.sadapay.assessment.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.sadapay.assessment.data.models.Items
import com.sadapay.assessment.network.RetrofitService
import com.sadapay.assessment.network.pagingsource.GithubTrendingPagingSource
import javax.inject.Inject
import javax.inject.Singleton


const val NETWORK_PAGE_SIZE = 25
const val MAX_SIZE = 200

@Singleton
class GitHubRepoRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService
) : GitHubRepoRepository{

    override fun getSearchResults(): LiveData<PagingData<Items>> {
        return  Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = MAX_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { GithubTrendingPagingSource(retrofitService) }
        ).liveData
    }

}