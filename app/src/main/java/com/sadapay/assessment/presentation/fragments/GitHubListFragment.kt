package com.sadapay.assessment.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.sadapay.assessment.adapters.GitTrendingReposAdapter
import com.sadapay.assessment.adapters.GitTrendingReposLoadStateAdapter
import com.sadapay.assessment.viewmodel.GitHubListViewModel
import com.sadapay.databinding.FragmentGitHubListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubListFragment : Fragment() {

    private lateinit var binding: FragmentGitHubListBinding
    private val viewModel: GitHubListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGitHubListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        initView()
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerViewContainer.startShimmer()
    }

    override fun onPause() {
        binding.shimmerViewContainer.stopShimmer()
        super.onPause()
    }

    private fun initView() {

        setHasOptionsMenu(true)
        val adapter = GitTrendingReposAdapter()

        binding.githubRepoRv.apply {
            setHasFixedSize(true)
            itemAnimator = null
            this.adapter = adapter.withLoadStateHeaderAndFooter(
                header = GitTrendingReposLoadStateAdapter { adapter.retry() },
                footer = GitTrendingReposLoadStateAdapter { adapter.retry() }
            )
        }

        binding.btnRetry.setOnClickListener {
            adapter.retry()
        }


        viewModel.repos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                shimmerViewContainer.isVisible = loadState.source.refresh is LoadState.Loading
                githubRepoRv.isVisible = loadState.source.refresh is LoadState.NotLoading
                llRetry.isVisible = loadState.source.refresh is LoadState.Error

                // no data
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    githubRepoRv.isVisible = false
                    llRetry.isVisible = true
                    shimmerViewContainer.isVisible = false
                    shimmerViewContainer.stopShimmer()

                } else {
                    shimmerViewContainer.stopShimmer()

                }
            }
        }
    }

}