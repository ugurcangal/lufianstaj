package com.ugurcangal.lufian.view.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ugurcangal.lufian.FirebaseInstance.firestore
import com.ugurcangal.lufian.databinding.AdminOrderItemBinding
import com.ugurcangal.lufian.model.Orders

class AdminOrdersAdapter(val orderList : ArrayList<Orders>) : RecyclerView.Adapter<AdminOrdersAdapter.AdminOrdersViewHolder>() {

    class AdminOrdersViewHolder(val binding: AdminOrderItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminOrdersViewHolder {
        val binding = AdminOrderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdminOrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminOrdersViewHolder, position: Int) {
        val order = orderList[position]
        val item = holder.binding

        item.userEmailTxt.text = order.user
        item.userAddress.text = order.address

        item.rejectOrderButton.setOnClickListener {
            val builderAlert = AlertDialog.Builder(it.context)
            builderAlert.setTitle("Sipariş İptal Edilecek!")
            builderAlert.setMessage("Emin misiniz?")
            builderAlert.setPositiveButton("İptal Et") { dialogInterface, i ->
                firestore.collection("Orders").document(order.id).delete()
            }.create()
            builderAlert.setNegativeButton("Vazgeç") { dialog , i ->
                dialog.dismiss()
            }
            builderAlert.create().show()
        }

        item.confirmOrderButton.setOnClickListener {
            val builderAlert = AlertDialog.Builder(it.context)
            builderAlert.setTitle("Sipariş Onaylanacak!")
            builderAlert.setMessage("Emin misiniz?")
            builderAlert.setPositiveButton("Onayla") { dialogInterface, i ->
                firestore.collection("Orders").document(order.id).update("status","approved")
            }.create()
            builderAlert.setNegativeButton("Vazgeç") { dialog , i ->
                dialog.dismiss()
            }
            builderAlert.create().show()
        }

        item.goToOrder.setOnClickListener {
            //////////////////////////////////////////////////////////////////
        }


    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}