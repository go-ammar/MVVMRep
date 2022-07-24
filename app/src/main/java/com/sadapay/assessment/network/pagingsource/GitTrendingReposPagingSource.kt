package com.sadapay.assessment.network.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadapay.assessment.data.models.Items
import com.sadapay.assessment.network.RetrofitService
import retrofit2.HttpException
import java.io.IOException

private const val REPOS_STARTING_PAGE_INDEX = 1

// PagingSource is the base class for loading snapshots of data into a stream of PagingData
class GithubTrendingPagingSource(
    private val githubApi: RetrofitService,
) : PagingSource<Int, Items>() {


    // load(params: LoadParams<Int>) function will be called by the Paging library to asynchronously
    // fetch more data to be displayed as the user scrolls around.
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Items> {
        val pageIndex = params.key ?: REPOS_STARTING_PAGE_INDEX

        return try {

            val response = githubApi.getTrendingRepos(
                page = pageIndex
            )

            val repos = response.items

//            This is where I am going to retrieve my items from a service (in my case, Retrofit)
//            and return them wrapped into a LoadResult type.
            return LoadResult.Page(
                data = repos,
                prevKey = if (pageIndex == REPOS_STARTING_PAGE_INDEX) null else pageIndex - 1,
                nextKey = if (repos.isEmpty()) null else pageIndex + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Items>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}