package com.ugurcangal.lufian.viewmodel.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ugurcangal.lufian.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminHomeViewModel @Inject constructor() : ViewModel() {

    private val firestore = Firebase.firestore
    private val storage = Firebase.storage
    var productArrayList = MutableLiveData<ArrayList<Product>>()

    fun getProduct(){
        viewModelScope.launch {
            firestore.collection("Product").addSnapshotListener { value, error ->
                value?.let {
                    val documents = value.documents

                    val productArrayList2 = ArrayList<Product>()

                    for (document in documents){
                        val id = document.id
                        val name = document.get("name") as String
                        val price = document.get("price") as String
                        val imageUrl = document.get("downloadUrl")

                        val product = Product(id,name,price,imageUrl.toString())
                        productArrayList2.add(product)
                        productArrayList.value = productArrayList2
                    }
                }

            }
        }
    }

//    fun deleteProduct(product: Product){
//        viewModelScope.launch {
//            storage.reference.child("images/${product.name}").delete()
//            firestore.collection("Product").document(product.id).delete()
//        }
//    }

    fun observeProductList() : LiveData<ArrayList<Product>>{
        return productArrayList
    }



}