package com.amv0107.fakestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.amv0107.fakestore.databinding.ActivityMainBinding
import com.amv0107.fakestore.hilt.service.ProductsServices
import com.amv0107.fakestore.model.domain.Product
import com.amv0107.fakestore.model.mapper.ProductMapper
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var productService: ProductsServices

    @Inject
    lateinit var productMapper: ProductMapper

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val controller = ProductEpoxyController()
        binding.epoxyRecyclerView.setController(controller)

        lifecycleScope.launchWhenStarted {
            val response = productService.getAllProducts()
            Log.i("DATA", response.body()!!.toString())
            val domainProducts: List<Product> = response.body()!!.map {
                productMapper.buildFrom(it)
            }
            controller.setData(domainProducts)
        }
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