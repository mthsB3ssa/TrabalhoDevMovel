package com.example.boardofmessageapp_kt2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.boardofmessageapp_kt2.data.db.accounts.UserAccountEntity
import com.example.boardofmessageapp_kt2.data.db.accounts.UserAccountsDao

/**
 * Created by me on 14/06/2021
 */
@Database(entities = arrayOf(UserAccountEntity::class), version = 3, exportSchema = false)
abstract class MyFinDatabase : RoomDatabase() {
    abstract fun userAccountsDao(): UserAccountsDao
}