package com.example.firebaseecommerce.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject137.databinding.ProductRowBinding
import com.squareup.picasso.Picasso
import gov.rajasthan.firstapp137.ProductModel

class RecyclerProductAdpater(val context: Context, val arrProductData: ArrayList<ProductModel>): RecyclerView.Adapter<RecyclerProductAdpater.ViewHolder>() {
    class ViewHolder(val binding: ProductRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrProductData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.binding.productImg.setImageResource()
        Picasso.get().load(arrProductData[position].productImg).into(holder.binding.productImg);
        holder.binding.productName.text = arrProductData[position].productTitle
        holder.binding.productPrice.text = "/$ ${arrProductData[position].productPrice.toString()}"

        holder.binding.productImg.setOnClickListener {
            /*val intent = Intent(context, ProductDetailsActivity::class.java)
            context.startActivity(intent)*/
        }
    }
}