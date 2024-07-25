package com.example.boardofmessageapp_kt2.data

import androidx.lifecycle.LiveData
import com.example.boardofmessageapp_kt2.data.db.MyFinDatabase
import com.example.boardofmessageapp_kt2.data.db.accounts.UserAccountEntity
import com.example.boardofmessageapp_kt2.data.network.BaseRepository
import com.example.boardofmessageapp_kt2.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by me on 14/06/2021
 */

class LiveAccountRepository
@Inject
constructor(
    private val db: MyFinDatabase,
    private var userData: UserDataManager
) : AccountRepository, BaseRepository() {

    // CRUD
    override fun insertAccount(userAccountObj: UserAccountEntity) {
        GlobalScope.launch(IO) {
            db.userAccountsDao()
                .insert(userAccountObj)
        }
    }

    override fun insertAccounts(userAccountObj: List<UserAccountEntity>) {
        GlobalScope.launch(IO) {
            db.userAccountsDao()
                .insertAll(userAccountObj)
        }
    }

    override fun getAllUserAccounts(): LiveData<List<UserAccountEntity>> =
        db.userAccountsDao().getAll()

    override fun deleteUserAccount(userAccountObj: UserAccountEntity) =
        db.userAccountsDao().delete(userAccountObj)

    override fun deleteAllUserAccounts() = db.userAccountsDao().deleteAll()
    override fun clearUserSessionData() {
        userData.clearUserSessionData()
        db.clearAllTables()
    }
}