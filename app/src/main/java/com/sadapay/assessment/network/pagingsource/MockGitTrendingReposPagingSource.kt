package com.sadapay.assessment.network.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadapay.assessment.data.models.Items
import com.sadapay.assessment.data.models.Owner
import retrofit2.HttpException
import java.io.IOException

private const val REPOS_STARTING_PAGE_INDEX = 1

class FakeGithubTrendingPagingSource : PagingSource<Int, Items>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Items> {
        val pageIndex = params.key ?: REPOS_STARTING_PAGE_INDEX

        return try {
            val repos = listOf(
                Items(
                    id = 1,
                    name = "sadapay",
                    description = "desc",
                    fullName = "fullName",
                    stars = 1,
                    language = "language",
                    owner = Owner(1, "login", "avatar_url")
                )
            )

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