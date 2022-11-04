package com.ugurcangal.lufian.viewmodel.user

import android.view.View
import com.bumptech.glide.Glide
import com.ugurcangal.lufian.BaseViewModel
import com.ugurcangal.lufian.databinding.FragmentUserProductBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProductViewModel @Inject constructor() : BaseViewModel(){

    fun getProduct(binding: FragmentUserProductBinding,productId: String,view : View){
        firestore.collection("Product").document(productId).get().addOnSuccessListener {
            binding.imageProgress.visibility = View.GONE
            binding.productName.text = it.get("name").toString()
            binding.productPrice.text = it.get("price").toString() + " TL"
            Glide.with(view).load(it.get("downloadUrl").toString()).into(binding.productImage)
        }
    }

}