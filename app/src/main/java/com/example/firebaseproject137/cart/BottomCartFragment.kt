package com.example.firebaseecommerce.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseproject137.databinding.FragmentBottomCartBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.snapshots
import gov.rajasthan.firstapp137.CartModel
import gov.rajasthan.firstapp137.OrderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import java.util.UUID

class BottomCartFragment : Fragment() {
    lateinit var binding: FragmentBottomCartBinding
    val arrCartList = ArrayList<CartModel>()
    var totalAmt = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBottomCartBinding.inflate(layoutInflater, container, false)


        GlobalScope.launch(Dispatchers.Main) {

            FirebaseFirestore
                .getInstance()
                .collection("Users")
                .document("oXgnWUTzX1fgadiRp6r13oOMt253")
                .collection("myCart").snapshots().collect { value ->


                    if(value.documents.size>0) {
                        arrCartList.clear()
                        totalAmt = 0.0

                        binding.cartRecycler.visibility = View.VISIBLE
                        binding.imgEmptyCart.visibility = View.GONE



                        for (eachCartItem in value.documents) {
                            val cartModel = eachCartItem.toObject(CartModel::class.java)
                            totalAmt += (cartModel!!.productQty*cartModel.productDiscountPrice)
                            arrCartList.add(cartModel)
                        }

                        binding.txtTotalAmt.text = "\u20B9 $totalAmt"


                        binding.cartRecycler.layoutManager = LinearLayoutManager(requireContext())
                        binding.cartRecycler.adapter =
                            RecyclerCartAdpater(requireContext(), arrCartList)
                    } else {
                        binding.cartRecycler.visibility = View.GONE
                        binding.imgEmptyCart.visibility = View.VISIBLE
                    }
                }
        }

        binding.btnCheckOut.setOnClickListener {

            var myOrder = OrderModel(
                UUID.randomUUID().toString(),
                arrCartList,
                totalAmt,
                "oXgnWUTzX1fgadiRp6r13oOMt253",
                1
            )

            FirebaseFirestore
                .getInstance()
                .collection("Orders")
                .add(myOrder).addOnSuccessListener {
                    Toast.makeText(requireContext(), "Order placed!!", Toast.LENGTH_SHORT).show()

                    //cart empty
                    for(eachCartItem in arrCartList){
                        FirebaseFirestore
                            .getInstance()
                            .collection("Users")
                            .document("oXgnWUTzX1fgadiRp6r13oOMt253")
                            .collection("myCart")
                            .document(eachCartItem.cartId)
                            .delete()
                    }

                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "Can't place your Order: ${it.message}", Toast.LENGTH_SHORT).show()
                }

        }









        // Inflate the layout for this fragment
        return binding.root
    }


}