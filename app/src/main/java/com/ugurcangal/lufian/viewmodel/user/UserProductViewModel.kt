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
    private var basketArray = arrayListOf<String>()

    fun getProduct(binding: FragmentUserProductBinding,productId: String,view : View){
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

    fun getBasket(binding: FragmentUserProductBinding,productId: String){
//        var basketMap: HashMap<String, String>
        firestore.collection("Basket").document(auth.currentUser!!.email.toString()).get().addOnSuccessListener {
            if (it.data?.isNotEmpty() == true){
                basketArray = it.get("basket") as ArrayList<String>
//                basketMap = it.get("size") as HashMap<String, String>
//                println(productId+ " " + basketMap.get(productId))
                if (basketArray.contains(productId)){
                    binding.addToBasketBtn.isEnabled = false
                }
            }
        }
    }

    fun addToBasket(productId: String,binding: FragmentUserProductBinding){
        val basket = hashMapOf<String,Any>()
//        var basketMap = hashMapOf<String,String>()
        firestore.collection("Basket").document(auth.currentUser!!.email.toString()).get().addOnSuccessListener {
            if (it.data?.isNotEmpty() == true){
                basketArray = it.get("basket") as ArrayList<String>
            }else{
                firestore.collection("Basket").document(auth.currentUser!!.email.toString()).set(basket)
            }
        }
        basketArray.add(productId)

//        basketMap.put(productId,"M")
        basket.put("basket",basketArray)
//        basket.put("size", basketMap)
        firestore.collection("Basket").document(auth.currentUser!!.email.toString()).update(basket).addOnSuccessListener {
            binding.addToBasketBtn.isEnabled = false
        }
    }

}