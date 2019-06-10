package io.coreflodev.exampleapplication.common.network

import io.coreflodev.exampleapplication.common.network.credit.Credit
import io.reactivex.Single
import retrofit2.http.GET

interface ClearScoreApi {

    @GET("mockcredit/values")
    fun getCredit(): Single<Credit>
}


