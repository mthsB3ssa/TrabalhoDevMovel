package com.example.boardofmessageapp_kt2.domain.repository

import com.example.boardofmessageapp_kt2.base.objects.MyFinBudget
import com.example.boardofmessageapp_kt2.data.model.BudgetDetailsResponse
import com.example.boardofmessageapp_kt2.data.model.FilteredResultsByPage
import com.example.boardofmessageapp_kt2.data.network.Resource

interface BudgetsRepository {
    suspend fun getBudgetsList(page: Int, pageSize: Int): Resource<FilteredResultsByPage<MyFinBudget>>
    suspend fun getBudgetDetails(
        id: String
    ): Resource<BudgetDetailsResponse>

    suspend fun updateBudgetCategoryAmounts(
        budgetId: String,
        catId: String,
        plannedExpenseAmount: String,
        plannedIncomeAmount: String
    ): Resource<Unit>
}
