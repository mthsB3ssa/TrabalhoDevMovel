package com.example.boardofmessageapp_kt2.domain.repository

import com.example.boardofmessageapp_kt2.data.model.AccountsListResponse
import com.example.boardofmessageapp_kt2.data.network.Resource

/**
 * Created by me on 05/09/2021
 */
interface AccountsRepository {
    suspend fun getAccountsList(): Resource<AccountsListResponse>
}