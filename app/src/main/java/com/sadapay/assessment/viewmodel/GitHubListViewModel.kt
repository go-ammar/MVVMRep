package com.sadapay.assessment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sadapay.assessment.repository.GitHubRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GitHubListViewModel @Inject constructor(
    private val repository: GitHubRepoRepository,
) : ViewModel() {

    private val repoData = MutableLiveData("")

    val repos = repoData.switchMap {
        repository.getSearchResults().cachedIn(viewModelScope)
    }

}