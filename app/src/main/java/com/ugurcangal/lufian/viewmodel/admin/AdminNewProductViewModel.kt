package com.ugurcangal.lufian.viewmodel.admin

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AdminNewProductViewModel @Inject constructor() : ViewModel() {

    private val auth = Firebase.auth
    private val firestore = Firebase.firestore
    private val storage = Firebase.storage

    fun uploadProduct(
        context: Context,
        selectedImage: Uri?,
        productName: String,
        price: String,
        color: String,
        category: String
    ) {
        val uuid = UUID.randomUUID()
        val imageName = productName

        val reference = storage.reference
        val imageReference = reference.child("images").child(imageName)
        if (selectedImage != null) {

            imageReference.putFile(selectedImage).addOnSuccessListener {

                val uploadPictureReference = storage.reference.child("images").child(imageName)
                uploadPictureReference.downloadUrl.addOnSuccessListener {

                    val productMap = hashMapOf<String, Any>()
                    productMap.put("downloadUrl", it.toString())
                    productMap.put("name", productName)
                    productMap.put("price", price)
                    productMap.put("color", color)
                    productMap.put("category", category)

                    firestore.collection("Product").add(productMap).addOnSuccessListener {
                        Toast.makeText(context, "Ürün Başarıyla Yüklendi!", Toast.LENGTH_SHORT).show()
                    }

                }
            }.addOnFailureListener {
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun logout(){
        auth.signOut()
    }
}