package com.jitendraalekar.githubgeek.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jitendraalekar.githubgeek.R
import com.jitendraalekar.githubgeek.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import android.app.Activity
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.jitendraalekar.githubgeek.data.model.GithubRepository
import com.jitendraalekar.githubgeek.util.isNetworkConnected
import com.jitendraalekar.githubgeek.ui.LinearItemDecoration
import com.jitendraalekar.githubgeek.ui.MainViewModel


@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    val mainViewModel by activityViewModels<MainViewModel>()
    private val dashboardListAdapter = DashboardListAdapter(::onRowClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDashboardBinding.inflate(inflater, container, false)
            .also { binding = it }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.repositories.observe(this) {
            setViewState(it)
        }
    }

    private fun setViewState(state: DashboardViewState) {
        when (state) {
            is DashboardViewState.Loading -> {
                binding.status = getString(R.string.loading)
            }
            is DashboardViewState.Content -> {
                binding.status = null
                dashboardListAdapter.submitList(state.list)

            }
            is DashboardViewState.Error -> {
                binding.status =
                    if (context?.isNetworkConnected() == true)
                        getString(R.string.error)
                    else
                        getString(R.string.network_error)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvRepos) {
            adapter = dashboardListAdapter
            addItemDecoration(LinearItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_24)))
        }
        setupSearchView(view)
    }

    private fun setupSearchView(view: View) {
        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                context?.let {
                    if (it.isNetworkConnected()) {
                        mainViewModel.onSearch(s)
                        hideKeyboardFrom(it, view)
                        view.clearFocus()
                    } else {
                        Toast.makeText(it, getString(R.string.no_internet), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    private fun onRowClick(githubRepository: GithubRepository) {
        findNavController().navigate(DashboardFragmentDirections.showDetail(githubRepository.id))
    }

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}