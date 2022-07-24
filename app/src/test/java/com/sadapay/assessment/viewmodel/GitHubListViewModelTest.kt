package com.sadapay.assessment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.sadapay.assessment.MainCoroutineRule
import com.sadapay.assessment.network.pagingsource.FakeGithubTrendingPagingSource
import com.sadapay.assessment.repository.GitHubRepoRepository
import com.sadapay.assessment.repository.MAX_SIZE
import com.sadapay.assessment.repository.MockGitTrendingReposRepository
import com.sadapay.assessment.repository.NETWORK_PAGE_SIZE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class GitHubListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val githubTrendingRepository = Mockito.mock(GitHubRepoRepository::class.java)

    private lateinit var viewModel: GitHubListViewModel

    @OptIn(ExperimentalPagingApi::class)
    @Before
    fun setup() {
        viewModel = GitHubListViewModel(MockGitTrendingReposRepository())
    }

    @Test
    fun liveDataWorking() {

        Mockito.`when`(githubTrendingRepository.getSearchResults()).thenReturn(
            Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    maxSize = MAX_SIZE,
                    enablePlaceholders = true
                ),
                pagingSourceFactory = { FakeGithubTrendingPagingSource() }
            ).liveData
        )

        viewModel.repos
    }

}