package com.example.boardofmessageapp_kt2.domain.repository

import androidx.lifecycle.LiveData
import com.example.boardofmessageapp_kt2.data.db.accounts.UserAccountEntity

interface AccountRepository {
    fun insertAccount(
        userAccountObj: UserAccountEntity
    )

    fun insertAccounts(
        userAccountObj: List<UserAccountEntity>
    )

    fun getAllUserAccounts(): LiveData<List<UserAccountEntity>>
    fun deleteUserAccount(
        userAccountObj: UserAccountEntity
    )

    fun deleteAllUserAccounts()
    fun clearUserSessionData()
}
