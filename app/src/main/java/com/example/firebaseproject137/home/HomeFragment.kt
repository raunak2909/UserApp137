package com.example.firebaseproject137.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseecommerce.category.RecyclerCategoryAdapter
import com.example.firebaseecommerce.category.RecyclerProductAdpater
import com.example.firebaseproject137.data.remote.AppRepository
import com.example.firebaseproject137.databinding.FragmentHomeBinding
import com.example.firebaseproject137.home.viewmodel.HomeFragmentViewModel
import com.example.firebaseproject137.home.viewmodel.HomeFragmentViewModelFactory
import com.google.firebase.firestore.FirebaseFirestore
import gov.rajasthan.firstapp137.CategoryModel
import gov.rajasthan.firstapp137.ProductModel
import gov.rajasthan.firstapp137.SubCategoryModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homeFragmentViewModel: HomeFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        homeFragmentViewModel = ViewModelProvider(
            this,
            HomeFragmentViewModelFactory(AppRepository())
        )[HomeFragmentViewModel::class.java]


        homeFragmentViewModel.getBanners()
        homeFragmentViewModel.getCategories()
        homeFragmentViewModel.getProducts()

        homeFragmentViewModel.mutableBannerData.observe(this.viewLifecycleOwner){banners->

        }

        homeFragmentViewModel.mutableCatData.observe(this.viewLifecycleOwner){cat->
            binding.recyclerCategory.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            binding.recyclerCategory.adapter =
                RecyclerCategoryAdapter(requireContext(), cat as ArrayList<CategoryModel>)
        }

        homeFragmentViewModel.mutableProductData.observe(this.viewLifecycleOwner){products->
            binding.recyclerProduct.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerProduct.adapter =
                RecyclerProductAdpater(requireContext(), products as ArrayList<ProductModel>)
        }


        /* var arrCatList =ArrayList<CategoryModal>().apply {
             add(CategoryModal(R.drawable.shoes, "shose"))
             add(CategoryModal(R.drawable.shoes, "shose"))
             add(CategoryModal(R.drawable.shoes, "shose"))
             add(CategoryModal(R.drawable.shoes, "shose"))
             add(CategoryModal(R.drawable.shoes, "shose"))
             add(CategoryModal(R.drawable.shoes, "shose"))
         }*/


        /*var firestore = FirebaseFirestore.getInstance()
        var arrCatList = ArrayList<CategoryModel>()
        var arrSubCatList = ArrayList<SubCategoryModel>()
        var arrProdList = ArrayList<ProductModel>()*/




        // Inflate the layout for this fragment
        return binding.root
    }

//    override fun onBackPressed() {
//        val exitDialog = AlertDialog.Builder(requireContext())
//
//        exitDialog.setCancelable(false)  //not cancel nahi kya ja sakta
//
//        exitDialog.setTitle("Exit")
//        exitDialog.setMessage("are you sure Exit")
//        exitDialog.setIcon(android.R.drawable.gallery_thumb)
//
//
//
//        exitDialog.setPositiveButton("Yes"
//        ) { p0, p1 -> super.onBackPressedDispatcher.onBackPressed()}
//
//        exitDialog.setNegativeButton("No"
//        ) { p0, p1 -> }
//
//        exitDialog.show()
//    }


}