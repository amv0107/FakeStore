package com.amv0107.fakestore

import androidx.core.view.isGone
import androidx.core.view.isVisible
import coil.load
import com.amv0107.fakestore.databinding.EpoxyModelProductItemBinding
import com.amv0107.fakestore.epoxy.ViewBindingKotlinModel
import com.amv0107.fakestore.model.domain.Product
import java.text.NumberFormat

data class ProductEpoxyModel(
    val product: Product
) : ViewBindingKotlinModel<EpoxyModelProductItemBinding>(R.layout.epoxy_model_product_item) {

    private val currencyFormat = NumberFormat.getCurrencyInstance()

    override fun EpoxyModelProductItemBinding.bind() {
        // Setup our text
        productTitleTextView.text = product.title
        productDescriptionTextView.text = product.description
        productCategoryTextView.text = product.category
        productPriceTextView.text = currencyFormat.format(product.price)

        // Load our image
        productImageViewLoadingProgressBar.isVisible = true
        productImageView.load(data = product.image) {
            listener { request, result ->
                productImageViewLoadingProgressBar.isGone = true
            }
        }
    }
}
