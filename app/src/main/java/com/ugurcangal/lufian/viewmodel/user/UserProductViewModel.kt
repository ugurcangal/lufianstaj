package com.ugurcangal.lufian.viewmodel.user

import android.content.Context
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ugurcangal.lufian.BaseViewModel
import com.ugurcangal.lufian.databinding.FragmentUserProductBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProductViewModel @Inject constructor() : BaseViewModel() {

    private var favorites = ArrayList<Any>()
    private val favoritesMap = HashMap<String, Any>()
    private var basketMap = HashMap<String, String>()

    fun getProduct(binding: FragmentUserProductBinding, productId: String, view: View) {
        firestore.collection("Product").document(productId).get().addOnSuccessListener {
            binding.imageProgress.visibility = View.GONE
            binding.productName.text = it.get("name").toString().replaceFirstChar {
                it.uppercase()
            }
            binding.productPrice.text = it.get("price").toString() + " TL"
            binding.productColor.text = "Renk : " + it.get("color").toString()
            Glide.with(view).load(it.get("downloadUrl").toString()).into(binding.productImage)
        }
    }

    fun favoriteControl(binding: FragmentUserProductBinding, productId: String) {
        firestore.collection("Favorites").document(auth.currentUser!!.email.toString())
            .addSnapshotListener { value, error ->
                value?.let {
                    favorites = it.get("favorites") as ArrayList<Any>
                    if (favorites.contains(productId)) {
                        binding.favoriteButton.visibility = View.GONE
                        binding.favoriteDeleteButton.visibility = View.VISIBLE
                    } else {

                    }

                }
            }
    }

    fun addFavorite(productId: String, view: View) {
        favorites.add(productId)
        favoritesMap.put("favorites", favorites)
        firestore.collection("Favorites").document(auth.currentUser!!.email.toString())
            .set(favoritesMap)
        view.visibility = View.GONE
    }

    fun deleteFavorite(binding: FragmentUserProductBinding, productId: String, view: View) {
        favorites.remove(productId)
        favoritesMap.put("favorites", favorites)
        firestore.collection("Favorites").document(auth.currentUser!!.email.toString())
            .update(favoritesMap)
        view.visibility = View.GONE
        binding.favoriteButton.visibility = View.VISIBLE
    }

    fun getBasket(binding: FragmentUserProductBinding, productId: String) {
        firestore.collection("Basket").document(auth.currentUser!!.email.toString()).get()
            .addOnSuccessListener {
                if (it.data?.isNotEmpty() == true) {
                    basketMap = it.get("basket") as HashMap<String, String>
                }
            }
    }

    fun addToBasket(context: Context,productId: String, binding: FragmentUserProductBinding, size: String) {
        val basket = hashMapOf<String, Any>()

        firestore.collection("Basket").document(auth.currentUser!!.email.toString()).get()
            .addOnSuccessListener {
                if (it.data?.isNotEmpty() == true) {
                    basketMap = it.get("basket") as HashMap<String, String>
                } else {
                    firestore.collection("Basket").document(auth.currentUser!!.email.toString())
                        .set(basket)
                }
            }

        basketMap.put(productId, size)
        basket.put("basket", basketMap)

        firestore.collection("Basket").document(auth.currentUser!!.email.toString()).set(basket)
            .addOnSuccessListener {
                Toast.makeText(context, "Ürün Sepete Eklendi.", Toast.LENGTH_SHORT).show()
            }
    }

}