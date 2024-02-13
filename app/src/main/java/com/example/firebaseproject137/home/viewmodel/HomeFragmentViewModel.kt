package com.example.firebaseproject137.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseproject137.data.remote.AppRepository
import com.example.firebaseproject137.models.BannerModel
import gov.rajasthan.firstapp137.CategoryModel
import gov.rajasthan.firstapp137.ProductModel
import kotlinx.coroutines.launch

class HomeFragmentViewModel(val appRepository: AppRepository)  : ViewModel(){
    var mutableCatData = MutableLiveData<List<CategoryModel>>()
    var mutableErrorData = MutableLiveData<String>()

    var mutableBannerData = MutableLiveData<List<BannerModel>>()
    var mutableProductData = MutableLiveData<List<ProductModel>>()


    fun getBanners(){
        mutableBannerData = appRepository.getBanners()
    }

    fun getCategories(){
        viewModelScope.launch {
            mutableCatData = appRepository.getCategories()
        }

    }

    fun getProducts(){
        mutableProductData = appRepository.getProducts()
    }


}