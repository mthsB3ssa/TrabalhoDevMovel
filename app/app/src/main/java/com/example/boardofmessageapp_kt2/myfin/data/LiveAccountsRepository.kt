package com.example.boardofmessageapp_kt2.data

import com.example.boardofmessageapp_kt2.data.UserDataManager
import com.example.boardofmessageapp_kt2.data.network.BaseRepository
import com.example.boardofmessageapp_kt2.data.network.MyFinAPIServices
import com.example.boardofmessageapp_kt2.domain.repository.AccountsRepository

/**
 * Created by me on 14/08/2021
 */
class LiveAccountsRepository(
    private val api: MyFinAPIServices,
    private val userData: UserDataManager
) : AccountsRepository, BaseRepository() {

    override suspend fun getAccountsList() = safeAPICall { api.getAccountsList() }
}