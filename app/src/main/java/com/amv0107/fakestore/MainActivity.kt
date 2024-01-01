package com.amv0107.fakestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import coil.load
import com.amv0107.fakestore.databinding.ActivityMainBinding
import com.amv0107.fakestore.hilt.service.ProductsServices
import com.amv0107.fakestore.model.domain.Product
import com.amv0107.fakestore.model.mapper.ProductMapper
import com.amv0107.fakestore.model.ui.UiProduct
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val controller = UiProductEpoxyController()
        binding.epoxyRecyclerView.setController(controller)
        controller.setData(emptyList())

        combine(
            viewModel.store.stateFlow.map { it.products },
            viewModel.store.stateFlow.map { it.favoriteProductsIds }
        ) { listOfProducts, setOfFavoriteIds ->
            listOfProducts.map { product ->
                UiProduct(product = product, isFavorite = setOfFavoriteIds.contains(product.id))
            }
        }.distinctUntilChanged().asLiveData().observe(this) { uiProducts ->
            controller.setData(uiProducts)
        }
        viewModel.refreshProducts()
    }


    private fun setupListeners() {
//        binding.cardView.setOnClickListener {
//            binding.productDescriptionTextView.apply {
//                isVisible = !isVisible
//            }
//        }

//        binding.addToCartButton.setOnClickListener {
//            binding.inCartView.apply {
//                isVisible = !isVisible
//            }
//        }

//        var isFavorite = false
//        binding.favoriteImageView.setOnClickListener {
//            val imageRes = if (isFavorite)
//                R.drawable.ic_round_favorite_border_24
//            else
//                R.drawable.ic_round_favorite_24
//            binding.favoriteImageView.setIconResource(imageRes)
//            isFavorite = !isFavorite
//        }
    }
}