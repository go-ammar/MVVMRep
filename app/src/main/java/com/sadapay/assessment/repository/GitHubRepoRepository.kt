package com.sadapay.assessment.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.sadapay.assessment.models.Items

interface GitHubRepoRepository {

    fun getSearchResults() : LiveData<PagingData<Items>>

}