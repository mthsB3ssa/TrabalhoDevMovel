package com.example.boardofmessageapp_kt2.data.model

import androidx.annotation.Keep
import com.example.boardofmessageapp_kt2.data.db.accounts.UserAccountEntity
import com.example.boardofmessageapp_kt2.data.network.BaseResponse

/**
 * Created by me on 21/12/2020
 */
@Keep
data class AttemptLoginResponse(
    val sessionkey: String?,
    val username: String?,
    val trustlimit: Int?,
    val accounts: List<UserAccountEntity>?
) : BaseResponse()