package com.ugurcangal.lufian.viewmodel.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ugurcangal.lufian.BaseViewModel
import com.ugurcangal.lufian.databinding.FragmentAdminOrderDetailsBinding

class AdminOrderDetailsViewModel : BaseViewModel() {

    var productList = MutableLiveData<ArrayList<HashMap<String,String>>>()

    fun getOrderDetails(id: String, binding: FragmentAdminOrderDetailsBinding){
        firestore.collection("Orders").document(id).addSnapshotListener { value, error ->
            value?.let {
                if (it.data?.isNotEmpty() == true){
                    productList.value = it.get("basket") as ArrayList<HashMap<String,String>>
                    binding.userTxt.text = it.get("user").toString()
                    binding.addressTxt.text = it.get("address").toString()
                }
            }
        }
    }

    fun observeBasketList() : LiveData<ArrayList<HashMap<String, String>>> {
        return productList
    }


}