package com.example.boardofmessageapp_kt2.domain.repository

import com.example.boardofmessageapp_kt2.data.model.MonthlyIncomeExpensesDistributionResponse
import com.example.boardofmessageapp_kt2.data.network.Resource

interface DashboardRepository {
    suspend fun getMonthlyExpensesIncomeDistribution(
        month: Int,
        year: Int
    ): Resource<MonthlyIncomeExpensesDistributionResponse>
}
