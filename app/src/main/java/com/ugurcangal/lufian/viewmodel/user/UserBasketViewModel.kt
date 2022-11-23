package com.ugurcangal.lufian.viewmodel.user

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ugurcangal.lufian.BaseViewModel
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentUserBasketBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserBasketViewModel @Inject constructor() : BaseViewModel() {

    var basketList = MutableLiveData<ArrayList<HashMap<String, String>>>()
    private var address : String? = null

    fun getTotalPrice(binding: FragmentUserBasketBinding) {
        firestore.collection("Basket").document(auth.currentUser!!.email.toString())
            .addSnapshotListener { value, error ->
                value?.let {
                    if (it.data?.isNotEmpty() == true) {
                        var totalPrice = 0
                        val basketList = it.get("basket") as ArrayList<HashMap<String, String>>
                        for (item in basketList) {

                            val itemprice = item.get("price")
                            val totitemPrice = item.get("piece")
                            if (itemprice != null && totitemPrice != null) {
                                totalPrice += itemprice.toInt() * totitemPrice.toInt()
                            }

                        }
                        binding.totalPrice.text = "Sepet Tutarı: \n ${totalPrice.toString()} TL "
                        binding.completeOrder.isEnabled = true
                        if (totalPrice == 0) {
                            binding.completeOrder.isEnabled = false
                        }
                    } else {
                        binding.totalPrice.text = "Sepet Tutarı: 0 TL"
                    }
                }
            }
    }

    fun getBasket() {
        firestore.collection("Basket").document(auth.currentUser!!.email.toString())
            .addSnapshotListener { value, error ->
                value?.let {
                    if (it.data?.isNotEmpty() == true) {
                        basketList.value = it.get("basket") as ArrayList<HashMap<String, String>>
                    }
                }
            }
    }

    fun checkAddress() {
        firestore.collection("Users").document(auth.currentUser!!.email.toString())
            .addSnapshotListener { value, error ->
                value?.let {
                    address = it.get("address") as String?
                }
            }
    }

    fun completeOrder(context: Context, view: View) {
        checkAddress()
        if (address != null) {
            val orderMap = HashMap<String, Any>()
            orderMap.put("address", address!!)
            orderMap.put("basket", basketList.value!!)
            orderMap.put("user", auth.currentUser!!.email.toString())
            firestore.collection("Orders").document().set(orderMap).addOnSuccessListener {
                val builderAlert = AlertDialog.Builder(context)
                builderAlert.setTitle("Siparişiniz Oluşturulmuştur")
                builderAlert.setMessage("Siparişiniz kargoya verildiğinde Sms ve Email olarak bilgilendirileceksiniz.")
                builderAlert.setPositiveButton("Tamam") { dialogInterface, i ->
                    Navigation.findNavController(view).navigate(R.id.userHomeFragment)
                    dialogInterface.dismiss()
                }.create().show()
                firestore.collection("Basket").document(auth.currentUser!!.email.toString())
                    .delete()
                basketList.value!!.clear()
            }
        } else {
            Navigation.findNavController(view).navigate(R.id.userProfileFragment)
            Toast.makeText(context, "Lütfen önce adres bilgilerinizi ekleyin!", Toast.LENGTH_SHORT)
                .show()
        }
    }


    fun observeBasketList(): LiveData<ArrayList<HashMap<String, String>>> {
        return basketList
    }


}