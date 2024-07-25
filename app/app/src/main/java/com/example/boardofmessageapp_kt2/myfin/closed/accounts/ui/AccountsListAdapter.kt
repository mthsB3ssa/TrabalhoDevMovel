package com.example.boardofmessageapp_kt2.closed.accounts.ui

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.boardofmessageapp_kt2.R
import com.example.boardofmessageapp_kt2.base.objects.MyFinAccount
import com.example.boardofmessageapp_kt2.databinding.AccountsListItemBinding
import com.example.boardofmessageapp_kt2.utils.MyFinUtils
import com.example.boardofmessageapp_kt2.utils.formatMoney
import com.example.boardofmessageapp_kt2.utils.setupBalanceStyle
import com.example.boardofmessageapp_kt2.utils.visible

/**
 * Created by me on 14/08/2021
 */
class AccountsListAdapter(
    private val context: Context,
    dataset: List<MyFinAccount>
) : ListAdapter<MyFinAccount, AccountsListAdapter.ViewHolder>(
    AsyncDifferConfig.Builder<MyFinAccount>(
        DiffCallback()
    ).build()
) {

    init {
        submitList(dataset)
    }

    /* DIFFUTIL */
    private class DiffCallback : DiffUtil.ItemCallback<MyFinAccount>() {
        override fun areItemsTheSame(oldItem: MyFinAccount, newItem: MyFinAccount) =
            oldItem.accountId == newItem.accountId

        override fun areContentsTheSame(oldItem: MyFinAccount, newItem: MyFinAccount) =
            oldItem == newItem
    }


    inner class ViewHolder(val binding: AccountsListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AccountsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            accountNameTv.text = item.name
            accountTypeTv.text = MyFinUtils.getReadableAccountTypeByTag(context, item.type)
            val balance = item.balance.toDoubleOrNull() ?: 0.0
            accountBalanceTv.text = formatMoney(balance)
            setupBalanceStyle(balance, accountBalanceTv)
            accountStatusTv.text = context.getString(if(item.isActive()) R.string.account_status_active else R.string.account_status_inactive)
            accountStatusWrapperCv.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    context,
                    if (item.isActive()) R.color.colorGreen else R.color.colorRed
                )
            )
            accountStatusWrapperCv.visible(true)
        }
    }
}