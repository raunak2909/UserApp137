package com.example.firebaseproject137.data.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.firebaseproject137.models.BannerModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import gov.rajasthan.firstapp137.CategoryModel
import gov.rajasthan.firstapp137.ProductModel

class AppRepository {

    val fireStoreDB : FirebaseFirestore = FirebaseFirestore.getInstance()
    val firesBaseAuth : FirebaseAuth = FirebaseAuth.getInstance()

    companion object{
        val COLLECTION_CAT = "category"
        val COLLECTION_SUB_CAT = "sub-category"
        val COLLECTION_PRODUCT = "product"
        val COLLECTION_BANNERS = "banners"
    }

    val mutableCatData = MutableLiveData<List<CategoryModel>>()
    val mutableErrorData = MutableLiveData<String>()

    val mutableBannerData = MutableLiveData<List<BannerModel>>()
    val mutableProductData = MutableLiveData<List<ProductModel>>()


    //login
    //signup

    //home
    //getBanners
    fun getBanners() : MutableLiveData<List<BannerModel>>{

        fireStoreDB.collection(COLLECTION_BANNERS).get().addOnSuccessListener {
            val arrBannerData = ArrayList<BannerModel>()
            for(banner in it.documents){
                arrBannerData.add(banner.toObject(BannerModel::class.java)!!)
            }
            mutableBannerData.postValue(arrBannerData)

        }.addOnFailureListener {
            val arrBannerData = ArrayList<BannerModel>()
            mutableBannerData.postValue(arrBannerData)
        }

        return mutableBannerData

    }

    //getCategory
    suspend fun getCategories() : MutableLiveData<List<CategoryModel>>{

        fireStoreDB.collection(COLLECTION_CAT).orderBy("createdAt").get().addOnSuccessListener {
            val arrCatData = ArrayList<CategoryModel>()
            for(cat in it.documents){
                arrCatData.add(cat.toObject(CategoryModel::class.java)!!)
            }
            mutableCatData.postValue(arrCatData)

        }.addOnFailureListener {
            val arrCatData = ArrayList<CategoryModel>()
            mutableErrorData.postValue("Error: ${it.message}")
            mutableCatData.postValue(arrCatData)
        }

        return mutableCatData

    }

    //getProduct
    fun getProducts() : MutableLiveData<List<ProductModel>>{

        fireStoreDB.collection(COLLECTION_PRODUCT).orderBy("createdAt").get().addOnSuccessListener {
            val arrProductData = ArrayList<ProductModel>()
            for(product in it.documents){
                arrProductData.add(product.toObject(ProductModel::class.java)!!)
            }
            mutableProductData.postValue(arrProductData)

        }.addOnFailureListener {
            val arrProductData = ArrayList<ProductModel>()
            mutableProductData.postValue(arrProductData)
        }

        return mutableProductData

    }

    fun getProductDetails(pid : String) : ProductModel{
        var productModel = ProductModel()

        fireStoreDB.collection(COLLECTION_PRODUCT).whereEqualTo("productId", pid).get().addOnSuccessListener {
            if(it.documents.size>0) {
                productModel = it.documents[0].toObject(ProductModel::class.java)!!
            }


        }.addOnFailureListener {
            Log.d("Product Details:", "Product not found!!")
        }

        return productModel


    }

}