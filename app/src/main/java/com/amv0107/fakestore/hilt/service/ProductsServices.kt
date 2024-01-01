package com.amv0107.fakestore.hilt.service

import com.amv0107.fakestore.model.network.NetworkProduct
import retrofit2.Response
import retrofit2.http.GET

interface ProductsServices {
    @GET("products")
    suspend fun getAllProducts(): Response<List<NetworkProduct>>
}