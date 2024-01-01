package com.amv0107.fakestore.model.ui

import com.amv0107.fakestore.model.domain.Product

data class UiProduct(
    val product: Product,
    val isFavorite: Boolean = false
)