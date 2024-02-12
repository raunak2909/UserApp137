package com.example.firebaseecommerce.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject137.ProductDetailsActivity
import com.example.firebaseproject137.databinding.ProductRowBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.DateTime
import com.squareup.picasso.Picasso
import gov.rajasthan.firstapp137.CartModel
import gov.rajasthan.firstapp137.ProductModel
import java.util.Calendar
import java.util.UUID

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
        holder.binding.productPrice.text = "\u20B9 ${arrProductData[position].productPrice.toString()}"

        holder.binding.productImg.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("pid", arrProductData[position].productId)
            context.startActivity(intent)
        }

        holder.binding.btnAdd.setOnClickListener {

            val cartId = UUID.randomUUID().toString()
            val discountedPrice = arrProductData[position].productPrice - (arrProductData[position].productPrice*(arrProductData[position].productDiscountPer/100))

            val time = Calendar.getInstance().timeInMillis

            FirebaseFirestore
                .getInstance()
                .collection("Users")
                .document("oXgnWUTzX1fgadiRp6r13oOMt253")
                .collection("myCart")
                .document(cartId)
                .set(CartModel(
                    cartId,
                    arrProductData[position].catId,
                    arrProductData[position].subCatId,
                    arrProductData[position].productId,
                    arrProductData[position].productTitle,
                    arrProductData[position].productDesc,
                    1,
                    arrProductData[position].productPrice,
                    discountedPrice,
                    arrProductData[position].productDiscountPer,
                    arrProductData[position].productImg,
                    "",
                    arrProductData[position].productUnit,
                    time
                ))
        }

    }
}