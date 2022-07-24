package com.sadapay.assessment.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.sadapay.assessment.data.models.Items
import com.sadapay.assessment.network.pagingsource.FakeGithubTrendingPagingSource

@ExperimentalPagingApi
class MockGitTrendingReposRepository : GitHubRepoRepository {

    override fun getSearchResults(): LiveData<PagingData<Items>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = MAX_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { FakeGithubTrendingPagingSource() }
        ).liveData
    }
}