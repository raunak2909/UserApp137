package com.example.firebaseecommerce.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject137.databinding.MycartRowBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import gov.rajasthan.firstapp137.CartModel

class RecyclerCartAdpater(val context: Context, val arrCart:ArrayList<CartModel>) : RecyclerView.Adapter<RecyclerCartAdpater.ViewHolder>() {
    class ViewHolder(val binding: MycartRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MycartRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrCart.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load(arrCart[position].productImg).into(holder.binding.proimg)
        holder.binding.catTitle.text =arrCart[position].productTitle
        holder.binding.productPrice.text =arrCart[position].productDiscountPrice.toString()
        holder.binding.txtQty.text = arrCart[position].productQty.toString()


        holder.binding.txtAdd.setOnClickListener {


            var qty = arrCart[position].productQty
            qty++

                    FirebaseFirestore
                        .getInstance()
                        .collection("Users")
                        .document("oXgnWUTzX1fgadiRp6r13oOMt253")
                        .collection("myCart")
                        .document(arrCart[position].cartId)
                        .update("productQty", qty).addOnSuccessListener {

                        }.addOnFailureListener {

                        }



        }

        holder.binding.txtSub.setOnClickListener {


            var qty = arrCart[position].productQty
            if(qty>1) {
                qty--

                FirebaseFirestore
                    .getInstance()
                    .collection("Users")
                    .document("oXgnWUTzX1fgadiRp6r13oOMt253")
                    .collection("myCart")
                    .document(arrCart[position].cartId)
                    .update("productQty", qty).addOnSuccessListener {

                    }.addOnFailureListener {

                    }
            } else {
                FirebaseFirestore
                    .getInstance()
                    .collection("Users")
                    .document("oXgnWUTzX1fgadiRp6r13oOMt253")
                    .collection("myCart")
                    .document(arrCart[position].cartId)
                    .delete()
            }



        }

        holder.binding.imgDelete.setOnClickListener{
            FirebaseFirestore
                .getInstance()
                .collection("Users")
                .document("oXgnWUTzX1fgadiRp6r13oOMt253")
                .collection("myCart")
                .document(arrCart[position].cartId)
                .delete()
        }




    }
}