package com.jitendraalekar.githubgeek.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jitendraalekar.githubgeek.R
import com.jitendraalekar.githubgeek.util.Result
import com.jitendraalekar.githubgeek.databinding.FragmentDetailBinding
import com.jitendraalekar.githubgeek.ui.LinearItemDecoration
import com.jitendraalekar.githubgeek.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val contributorListAdapter = ContributorListAdapter()
    private val issueListAdapter = IssueListAdapter()
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailBinding.inflate(inflater, container, false)
            .also { binding = it }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.fetchRepo(args.repoId)?.let {
            detailViewModel.load(it.full_name)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()

        mainViewModel.fetchRepo(args.repoId)?.let {
            binding.repo = it
        }
        setupContributorView()
        setupIssueView()
    }

    private fun setupIssueView() {
        detailViewModel.issues.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    binding.issueStatus =
                        if(it.data.isEmpty()) {
                            getString(R.string.no_issues)
                        }else
                            getString(R.string.issues)
                    issueListAdapter.submitList(it.data)
                }
                is Result.Loading -> {
                    binding.issueStatus = getString(R.string.loading_issues)
                }
                is Result.Error -> {
                    binding.issueStatus = getString(R.string.error_issues)
                }
            }
        }
    }

    private fun setupContributorView() {
        detailViewModel.contributor.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    binding.contributorStatus = getString(R.string.contributors)
                    contributorListAdapter.submitList(it.data)
                }
                is Result.Loading -> {
                    binding.contributorStatus = getString(R.string.loading_contributors)
                }
                is Result.Error -> {
                    binding.contributorStatus = getString(R.string.error_contributors)
                }
            }
        }
    }

    private fun setupViews() {
        with(binding.rvContributors) {
            adapter = contributorListAdapter
            addItemDecoration(LinearItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_24)))
        }
        with(binding.rvIssues) {
            adapter = issueListAdapter
            addItemDecoration(LinearItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_24)))
        }
    }

}