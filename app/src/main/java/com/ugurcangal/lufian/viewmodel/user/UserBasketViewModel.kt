package com.ugurcangal.lufian.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugurcangal.lufian.BaseViewModel
import com.ugurcangal.lufian.databinding.FragmentUserBasketBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserBasketViewModel @Inject constructor() : BaseViewModel() {

    var basketList = MutableLiveData<ArrayList<HashMap<String,String>>>()

    fun getTotalPrice(binding: FragmentUserBasketBinding){
        firestore.collection("Basket").document(auth.currentUser!!.email.toString()).addSnapshotListener { value, error ->
            value?.let {
                if (it.data?.isNotEmpty() == true){
                    var totalPrice = 0
                    val price = it.get("basket") as ArrayList<HashMap<String,String>>
                    for (item in price){
                        val itemprice = item.get("price")
                        if (itemprice != null) {
                            totalPrice += itemprice.toInt()
                        }
                    }
                    binding.totalPrice.text = "Sepet Tutarı: \n ${totalPrice.toString()} TL "
                    binding.completeOrder.isEnabled = true
                    if (totalPrice == 0){
                        binding.completeOrder.isEnabled = false
                    }
                }else{
                    binding.totalPrice.text = "Sepet Tutarı: 0 TL"
                }
            }
        }
    }

    fun getBasket(){
        firestore.collection("Basket").document(auth.currentUser!!.email.toString()).addSnapshotListener { value, error ->
            value?.let {
                if (it.data?.isNotEmpty() == true){
                    basketList.value = it.get("basket") as ArrayList<HashMap<String,String>>
                }
            }
        }
    }

    fun observeBasketList() : LiveData<ArrayList<HashMap<String,String>>>{
        return basketList
    }

}