package com.amv0107.fakestore.redux

import com.amv0107.fakestore.model.domain.Product

data class ApplicationState(
    val products: List<Product> = emptyList(),
    val favoriteProductsIds: Set<Int> = emptySet()
)
