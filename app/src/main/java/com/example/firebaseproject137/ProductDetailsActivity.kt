package com.example.firebaseproject137

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.firebaseecommerce.productDetails.ColorModal
import com.example.firebaseproject137.data.remote.AppRepository
import com.example.firebaseproject137.databinding.ActivityProductDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import gov.rajasthan.firstapp137.ProductModel

class ProductDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailsBinding



    val arrColors = ArrayList<ColorModal>().apply {
        add(ColorModal("F57C00", R.color.orange))
        add(ColorModal("7B1FA2", R.color.purple))
        add(ColorModal("1976D2", R.color.blue))
        add(ColorModal("ACA298", R.color.grey))
        add(ColorModal("C2185B", R.color.pink))
        add(ColorModal("D32F2F", R.color.red))
        add(ColorModal("FFFFFF", R.color.white))
        add(ColorModal("000000", R.color.black))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pid = intent.getStringExtra("pid")



        var productModel = ProductModel()

        FirebaseFirestore.getInstance().collection(AppRepository.COLLECTION_PRODUCT).whereEqualTo("productId", pid).get().addOnSuccessListener {
            if(it.documents.size>0) {
                productModel = it.documents[0].toObject(ProductModel::class.java)!!
                //setting slider
                var bannerList = ArrayList<SlideModel>()
                for(eachImg in productModel.productImages){
                    bannerList.add(SlideModel(eachImg, "", ScaleTypes.FIT))
                }

                binding.bannerImageSlider.setImageList(bannerList)

                //setting title
                binding.txtProductTitle.text = productModel.productTitle

                //setting price
                binding.txtProductPrice.text = productModel.productPrice.toString()
                binding.txtProductPrice.paintFlags = binding.txtProductPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


                val discountedPrice = productModel.productPrice - (productModel.productPrice*(productModel.productDiscountPer/100))

                //setting disCountedPrice
                binding.txtProductDiscountedPrice.text = discountedPrice.toString()



                binding.recyclerColor.apply {
                    layoutManager = LinearLayoutManager(this@ProductDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
                    adapter = RecyclerColorAdapter(this@ProductDetailsActivity, arrColors)
                }

            }


        }.addOnFailureListener {
            Log.d("Product Details:", "Product not found!!")
        }








       /* binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Description"
                }
                1 -> {
                    tab.text = "Specifications"
                }
                else -> {
                    tab.text = "Reviews"
                }
            }
        }.attach()

        binding.viewPager.currentItem = 0*/


    }
}