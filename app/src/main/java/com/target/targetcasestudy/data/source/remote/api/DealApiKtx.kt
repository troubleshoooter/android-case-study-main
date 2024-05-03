package com.target.targetcasestudy.data.source.remote.api

import com.target.targetcasestudy.data.source.remote.model.DealResponse
import com.target.targetcasestudy.data.source.remote.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface DealApiKtx {

  @GET("deals")
  suspend fun retrieveDeals(): DealResponse

  @GET("deals/{dealId}")
  suspend fun retrieveDeal(@Path("dealId") dealId: Int): Product
}
