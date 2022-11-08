package com.ugurcangal.lufian.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugurcangal.lufian.databinding.UserBasketItemBinding

class UserBasketAdapter(var basketList : ArrayList<HashMap<String,String>>) : RecyclerView.Adapter<UserBasketAdapter.BasketAdapterViewHolder>() {

    class BasketAdapterViewHolder(val binding: UserBasketItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapterViewHolder {
        val binding = UserBasketItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BasketAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketAdapterViewHolder, position: Int) {
        val product = basketList[position]
        val item = holder.binding


        item.basketProductNameTV.text = product.get("name")?.replaceFirstChar {
            it.uppercase()
        }
        item.basketProductPriceTV.text = product.get("price") + " TL"
        item.basketProductSizeTV.text = "Beden: " + product.get("size")
        Glide.with(holder.itemView.context).load(product.get("downloadUrl")).into(item.basketIV)





    }

    override fun getItemCount(): Int {
        return basketList.size
    }
}