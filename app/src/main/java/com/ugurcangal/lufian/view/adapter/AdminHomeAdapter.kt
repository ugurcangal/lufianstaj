package com.ugurcangal.lufian.view.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ugurcangal.lufian.databinding.ProductItemBinding
import com.ugurcangal.lufian.model.Product
import kotlin.coroutines.coroutineContext

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
        item.deleteButton.setOnClickListener {
            val builderAlert = AlertDialog.Builder(it.context)
                builderAlert.setTitle("Ürünü Sil")
                builderAlert.setMessage("Ürün Silinecek, Emin misiniz?")
                builderAlert.setPositiveButton("Sil") { dialogInterface, i ->
                    val firestore = Firebase.firestore
                    val storage = Firebase.storage
                    storage.reference.child("images/${productList[position].name}").delete()
                    firestore.collection("Product").document(productList[position].id).delete()
                }.create()
            builderAlert.setNegativeButton("Vazgeç") { dialog , i ->
                dialog.dismiss()
            }
            builderAlert.create().show()


        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setList(newList : ArrayList<Product>){
        this.productList = newList
        notifyDataSetChanged()
    }

}