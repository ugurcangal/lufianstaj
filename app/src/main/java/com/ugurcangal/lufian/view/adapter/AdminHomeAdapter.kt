package com.ugurcangal.lufian.view.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ugurcangal.lufian.databinding.AdminProductItemBinding
import com.ugurcangal.lufian.model.Product

class AdminHomeAdapter() : RecyclerView.Adapter<AdminHomeAdapter.AdminHomeViewHolder>() {

    private var productList = ArrayList<Product>()

    class AdminHomeViewHolder(val binding: AdminProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminHomeViewHolder {
        val binding = AdminProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdminHomeViewHolder(binding)

    }


    private val diffUtil = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,diffUtil)


    override fun onBindViewHolder(holder: AdminHomeViewHolder, position: Int) {
        if (differ.currentList.size < 1){
            holder.itemView.visibility = View.GONE
        }
        val product = differ.currentList[position]
        val item = holder.binding
        item.productName.text = product.name
        item.productPrice.text = product.price + " " +"TL"
        Glide.with(holder.itemView.context).load(product.imageUrl).into(item.productImage)

        item.deleteButton.setOnClickListener {
            val builderAlert = AlertDialog.Builder(it.context)
            builderAlert.setTitle("Ürünü Sil")
            builderAlert.setMessage("Ürün Silinecek, Emin misiniz?")
            builderAlert.setPositiveButton("Sil") { dialogInterface, i ->
                    val firestore = Firebase.firestore
                    val storage = Firebase.storage
                    storage.reference.child("images/${product.name}").delete()
                    firestore.collection("Product").document(product.id).delete().addOnSuccessListener {
                        Toast.makeText(holder.itemView.context, "Ürün Başarıyla Silindi", Toast.LENGTH_SHORT).show()
                        notifyDataSetChanged()
                    }

                }.create()
            builderAlert.setNegativeButton("Vazgeç") { dialog , i ->
                dialog.dismiss()
            }
            builderAlert.create().show()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}