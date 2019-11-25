package com.test.slayde.myapplication

import com.test.slayde.myapplication.model.RestBitcoinResponse
import retrofit2.Call
import retrofit2.http.GET



interface BitcoinApi{
    @GET("v1/bpi/currentprice.json")
    fun getCurrentPrice(): Call<RestBitcoinResponse>
}