package com.example.boardofmessageapp_kt2.data.model

import androidx.annotation.Keep
import com.example.boardofmessageapp_kt2.base.objects.MyFinCategory
import com.example.boardofmessageapp_kt2.data.network.BaseResponse
import com.google.gson.annotations.SerializedName

@Keep
class MonthlyIncomeExpensesDistributionResponse(
    @SerializedName("categories")
    val categories: List<MyFinCategory>,
    @SerializedName("last_update_timestamp")
    val lastUpdateTimestamp: Long
) : BaseResponse() 
