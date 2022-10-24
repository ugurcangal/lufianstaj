package com.ugurcangal.lufian.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugurcangal.lufian.databinding.ProductItemBinding
import com.ugurcangal.lufian.model.Product

class AdminHomeAdapter() : RecyclerView.Adapter<AdminHomeAdapter.AdminHomeViewHolder>() {

    private var productList = ArrayList<Product>()

    class AdminHomeViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminHomeViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdminHomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminHomeViewHolder, position: Int) {
        val item = holder.binding
        item.productName.text = productList[position].name
        item.productPrice.text = productList[position].price + " " +"TL"
        Glide.with(holder.itemView.context).load(productList[position].imageUrl).into(item.productImage)

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setList(newList : ArrayList<Product>){
        this.productList = newList
        notifyDataSetChanged()
    }
}