package com.example.firebaseecommerce.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject137.databinding.CategoryRowBinding
import com.squareup.picasso.Picasso
import gov.rajasthan.firstapp137.CategoryModel

class RecyclerCategoryAdapter(val context: Context, val arrCatList:ArrayList<CategoryModel>) : RecyclerView.Adapter<RecyclerCategoryAdapter.ViewHolder>() {
    class ViewHolder (val binding: CategoryRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }


    override fun getItemCount(): Int {
        return arrCatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        //holder.binding.catImg.setImageResource(arrCatList[position].catImg)
        Picasso.get().load(arrCatList[position].catImg).into(holder.binding.catImg)

        holder.binding.catName.text =arrCatList[position].catName

        holder.binding.catProduct.setOnClickListener {
            /*val intent = Intent(context, CategoryPItemActivity::class.java)
            context.startActivity(intent)*/
        }
    }
}