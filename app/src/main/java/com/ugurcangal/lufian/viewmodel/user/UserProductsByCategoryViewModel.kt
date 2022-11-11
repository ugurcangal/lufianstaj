package com.ugurcangal.lufian.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ugurcangal.lufian.BaseViewModel
import com.ugurcangal.lufian.model.Product
import kotlinx.coroutines.launch

class UserProductsByCategoryViewModel : BaseViewModel() {

    var productArrayList = MutableLiveData<ArrayList<Product>>()

    fun getProductForCategory(category : String){
        viewModelScope.launch {
            firestore.collection("Product").whereEqualTo("category",category).addSnapshotListener { value, error ->
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

    fun observeProductList() : LiveData<ArrayList<Product>> {
        return productArrayList
    }

}