package com.example.boardofmessageapp_kt2.closed.accounts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardofmessageapp_kt2.base.BaseFragment
import com.example.boardofmessageapp_kt2.base.objects.MyFinAccount
import com.example.boardofmessageapp_kt2.data.network.Resource
import com.example.boardofmessageapp_kt2.databinding.FragmentAccountsBinding
import com.example.boardofmessageapp_kt2.utils.visible
import com.google.android.material.tabs.TabLayout

/**
 * Created by me on 14/08/2021
 */
class AccountsFragment :
    BaseFragment() {
    private lateinit var binding: FragmentAccountsBinding
    private val viewModel: AccountsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        getAccountsList()
    }

    private fun bindObservers() {
        viewModel.getAccountsListResponse().observe(viewLifecycleOwner) {
            binding.loadingPb.root.visible(it is Resource.Loading)
            when (it) {
                is Resource.Failure -> Toast.makeText(
                    requireContext(),
                    it.errorMessage,
                    Toast.LENGTH_LONG
                ).show()
                is Resource.Success -> {
                    setupTabLayout()
                }
                else -> {}
            }
        }

        viewModel.getAccountsListData().observe(viewLifecycleOwner) {
            setupAccountsListRecyclerView(it)
        }
    }

    private fun setupTabLayout() {
        binding.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewModel.onTabSelected(tab.position)
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setupAccountsListRecyclerView(accsList: List<MyFinAccount>) {
        context?.let {
            if (accsList.isNullOrEmpty()) {
                binding.accountsListEmptyViewTv.visible(true)
                binding.accountsListRv.visible(false)
            } else {
                binding.accountsListEmptyViewTv.visible(false)
                binding.accountsListRv.visible(true)
                binding.accountsListRv.layoutManager = LinearLayoutManager(context)
                if (binding.accountsListRv.adapter == null)
                    binding.accountsListRv.adapter = AccountsListAdapter(it, accsList)
                else
                    (binding.accountsListRv.adapter as AccountsListAdapter).submitList(accsList)
                binding.accountsListRv.addItemDecoration(
                    DividerItemDecoration(
                        binding.accountsListRv.context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }

        }
    }

    private fun getAccountsList() {
        viewModel.requestAccountsList()
    }
}

@Composable
fun AccountsPreview() {
    Column {
        Text(text = "Preview do Jetpack Compose")
        // Adicione outros elementos conforme necessário
    }
}

@Preview(showBackground = true)
@Composable

fun AccountsPreviewPreview() {
    AccountsPreview()
}