package com.amv0107.fakestore

import com.amv0107.fakestore.hilt.service.ProductsServices
import com.amv0107.fakestore.model.domain.Product
import com.amv0107.fakestore.model.mapper.ProductMapper
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsServices: ProductsServices,
    private val productMapper: ProductMapper
){

    suspend fun fetchAllProducts(): List<Product> {
        // todo better error handling
        return productsServices.getAllProducts().body()?.let { networkProducts ->
            networkProducts.map { productMapper.buildFrom(it) }
        } ?: emptyList()
    }
}