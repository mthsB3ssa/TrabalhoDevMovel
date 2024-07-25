package com.example.boardofmessageapp_kt2.di

import com.example.boardofmessageapp_kt2.domain.repository.AccountsRepository
import com.example.boardofmessageapp_kt2.data.LiveAccountsRepository
import com.example.boardofmessageapp_kt2.domain.repository.BudgetsRepository
import com.example.boardofmessageapp_kt2.data.LiveBudgetsRepository
import com.example.boardofmessageapp_kt2.domain.repository.DashboardRepository
import com.example.boardofmessageapp_kt2.data.LiveDashboardRepository
import com.example.boardofmessageapp_kt2.data.LiveTransactionsRepository
import com.example.boardofmessageapp_kt2.domain.repository.TransactionsRepository
import com.example.boardofmessageapp_kt2.data.LiveAccountRepository
import com.example.boardofmessageapp_kt2.domain.repository.AccountRepository
import com.example.boardofmessageapp_kt2.data.UserDataManager
import com.example.boardofmessageapp_kt2.data.db.MyFinDatabase
import com.example.boardofmessageapp_kt2.data.network.MyFinAPIServices
import com.example.boardofmessageapp_kt2.data.LiveLoginRepository
import com.example.boardofmessageapp_kt2.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by me on 05/09/2021
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideLoginRepository(
        service: MyFinAPIServices,
        userData: UserDataManager,
        db: MyFinDatabase
    ): LoginRepository = LiveLoginRepository(service, userData, db)

    @Singleton
    @Provides
    fun provideTransactionsRepository(
        service: MyFinAPIServices): TransactionsRepository = LiveTransactionsRepository(service)

    @Singleton
    @Provides
    fun provideAccountsRepository(
        service: MyFinAPIServices,
        userData: UserDataManager
    ): AccountsRepository = LiveAccountsRepository(service, userData)

    @Singleton
    @Provides
    fun provideDashboardRepository(
        service: MyFinAPIServices,
        userData: UserDataManager
    ): DashboardRepository = LiveDashboardRepository(service, userData)

    @Singleton
    @Provides
    fun provideBudgetsRepository(
        service: MyFinAPIServices,
        userData: UserDataManager
    ): BudgetsRepository = LiveBudgetsRepository(service, userData)

    @Singleton
    @Provides
    fun providePrivateRepository(
        db: MyFinDatabase,
        userData: UserDataManager
    ): AccountRepository = LiveAccountRepository(db, userData)
}