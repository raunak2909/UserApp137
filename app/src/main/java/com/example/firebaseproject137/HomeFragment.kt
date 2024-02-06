package com.example.firebaseproject137
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseecommerce.category.RecyclerCategoryAdapter
import com.example.firebaseecommerce.category.RecyclerProductAdpater
import com.example.firebaseproject137.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import gov.rajasthan.firstapp137.CategoryModel
import gov.rajasthan.firstapp137.ProductModel
import gov.rajasthan.firstapp137.SubCategoryModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


       /* var arrCatList =ArrayList<CategoryModal>().apply {
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
        }*/


        var firestore = FirebaseFirestore.getInstance()
        var arrCatList =ArrayList<CategoryModel>()
        var arrSubCatList =ArrayList<SubCategoryModel>()
        var arrProdList =ArrayList<ProductModel>()

        firestore
            .collection("category").get().addOnSuccessListener {

                for(cat in it.documents){
                    arrCatList.add(cat.toObject(CategoryModel::class.java)!!)
                }

                binding.recyclerCategory.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                binding.recyclerCategory.adapter = RecyclerCategoryAdapter(requireContext(), arrCatList)

            }.addOnFailureListener {

            }





        firestore
            .collection("product").get().addOnSuccessListener {

                for(subCat in it.documents){
                    arrProdList.add(subCat.toObject(ProductModel::class.java)!!)
                }

                binding.recyclerProduct.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recyclerProduct.adapter = RecyclerProductAdpater(requireContext(), arrProdList)

            }.addOnFailureListener {

            }








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