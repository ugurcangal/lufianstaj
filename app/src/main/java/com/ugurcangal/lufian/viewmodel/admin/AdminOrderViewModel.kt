package com.ugurcangal.lufian.viewmodel.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ugurcangal.lufian.BaseViewModel
import com.ugurcangal.lufian.model.Orders

class AdminOrderViewModel : BaseViewModel() {

    var orderList = MutableLiveData<ArrayList<Orders>>()

    fun getOrders(){
        firestore.collection("Orders").addSnapshotListener { value, error ->
            value?.let {

                val orderArray = ArrayList<Orders>()

                for (document in value.documents){
                    val status = document.get("status")
                    if (status != "approved"){
                        val id = document.id
                        val userEmail = document.get("user")
                        val address = document.get("address")

                        val order = Orders(id,userEmail.toString(),address.toString())
                        orderArray.add(order)
                        orderList.value = orderArray
                    }

                }
            }
        }
    }

    fun observeOrderList() : LiveData<ArrayList<Orders>>{
        return orderList
    }
}