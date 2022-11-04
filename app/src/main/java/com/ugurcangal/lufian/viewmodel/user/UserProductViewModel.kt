package com.ugurcangal.lufian.viewmodel.user

import android.view.View
import com.bumptech.glide.Glide
import com.ugurcangal.lufian.BaseViewModel
import com.ugurcangal.lufian.databinding.FragmentUserProductBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProductViewModel @Inject constructor() : BaseViewModel(){

    private var favorites = ArrayList<Any>()
    private val favoritesMap = HashMap<String,Any>()

    fun getProduct(binding: FragmentUserProductBinding,productId: String,view : View){
        firestore.collection("Product").document(productId).get().addOnSuccessListener {
            binding.imageProgress.visibility = View.GONE
            binding.productName.text = it.get("name").toString().uppercase()
            binding.productPrice.text = it.get("price").toString() + " TL"
            Glide.with(view).load(it.get("downloadUrl").toString()).into(binding.productImage)
        }
    }

    fun favoriteControl(binding: FragmentUserProductBinding, productId: String){
        firestore.collection("Favorites").document(auth.currentUser!!.email.toString()).addSnapshotListener { value, error ->
            value?.let {
                favorites = it.get("favorites") as ArrayList<Any>
                if (favorites.contains(productId)){
                    binding.favoriteButton.visibility = View.GONE
                    binding.favoriteDeleteButton.visibility = View.VISIBLE
                }else{

                }

            }
        }
    }

    fun addFavorite(productId: String, view: View){
        favorites.add(productId)
        favoritesMap.put("favorites",favorites)
        firestore.collection("Favorites").document(auth.currentUser!!.email.toString()).set(favoritesMap)
        view.visibility = View.GONE
    }

    fun deleteFavorite(binding: FragmentUserProductBinding,productId: String, view: View){
        favorites.remove(productId)
        favoritesMap.put("favorites",favorites)
        firestore.collection("Favorites").document(auth.currentUser!!.email.toString()).update(favoritesMap)
        view.visibility = View.GONE
        binding.favoriteButton.visibility = View.VISIBLE
    }

}