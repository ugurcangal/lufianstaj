package com.ugurcangal.lufian.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugurcangal.lufian.FirebaseInstance
import com.ugurcangal.lufian.databinding.AdminOrderDetailsItemBinding

class AdminOrderDetailsAdapter(var basketList : ArrayList<HashMap<String,String>>) :
    RecyclerView.Adapter<AdminOrderDetailsAdapter.OrderDetailsViewHolder>() {

    var availableList = ArrayList<HashMap<String,String>>()

    class OrderDetailsViewHolder (val binding: AdminOrderDetailsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailsViewHolder {
        val binding = AdminOrderDetailsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrderDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderDetailsViewHolder, position: Int) {
        val product = basketList[position]
        val item = holder.binding



        item.basketProductNameTV.text = product.get("name")?.replaceFirstChar {
            it.uppercase()
        }
        item.productPiece.text = product.get("piece") + " Adet"
        item.basketProductPriceTV.text = product.get("price") + " TL"
        item.basketProductSizeTV.text = "Beden: " + product.get("size")
        item.productId.text = "Ürün ID: " + product.get("id")
        Glide.with(holder.itemView.context).load(product.get("downloadUrl")).into(item.basketIV)


        FirebaseInstance.firestore.collection("Basket").document(FirebaseInstance.auth.currentUser!!.email.toString()).addSnapshotListener { value, error ->
            value?.let {
                if (it.data?.isNotEmpty() == true){
                    availableList = it["basket"] as ArrayList<HashMap<String, String>>
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return basketList.size
    }


}