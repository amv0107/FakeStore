package com.amv0107.fakestore.hilt.service

import retrofit2.Response
import retrofit2.http.GET

interface ProductsServices {
    @GET("products")
    suspend fun getAllProducts(): Response<List<Any>>
}