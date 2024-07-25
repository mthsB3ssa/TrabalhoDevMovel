package com.example.boardofmessageapp_kt2.data

import com.example.boardofmessageapp_kt2.data.UserDataManager
import com.example.boardofmessageapp_kt2.data.network.BaseRepository
import com.example.boardofmessageapp_kt2.data.network.MyFinAPIServices
import com.example.boardofmessageapp_kt2.domain.repository.DashboardRepository

class LiveDashboardRepository(
    private val api: MyFinAPIServices,
    private val userData: UserDataManager
) : DashboardRepository, BaseRepository() {

    override suspend fun getMonthlyExpensesIncomeDistribution(
        month: Int,
        year: Int
    ) = safeAPICall {
        api.getMonthlyExpensesIncomeDistribution(month, year)
    }
}