package com.ugurcangal.lufian.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.model.Product
import com.ugurcangal.lufian.view.adapter.UserFavoriteAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFavoritesViewModel @Inject constructor() : ViewModel() {

    private val firestore = Firebase.firestore
    private val auth = Firebase.auth
    var favoritesList = MutableLiveData<ArrayList<Product>>()

    fun getFavoriteProduct(){
            firestore.collection("Product").addSnapshotListener { value, error ->
                value?.let {
                    val documents = value.documents

                    val productArrayList2 = ArrayList<Product>()

                    for (document in documents) {
                        firestore.collection("Favorites").document(auth.currentUser!!.email.toString()).addSnapshotListener { value, error ->
                            value?.let {
                                var favorites: ArrayList<String> = it.data?.get("favorites") as ArrayList<String>

                                if (favorites.contains(document.id)){
                                    val id = document.id
                                    val name = document.get("name") as String
                                    val price = document.get("price") as String
                                    val imageUrl = document.get("downloadUrl")

                                    val product = Product(id, name, price, imageUrl.toString())
                                    productArrayList2.add(product)
                                    favoritesList.value = productArrayList2
                                }
                            }
                        }
                    }
                }
            }
    }

    fun observeProductList() : LiveData<ArrayList<Product>> {
        return favoritesList
    }

}