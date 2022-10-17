package com.ugurcangal.lufian.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.RegisterActivity
import com.ugurcangal.lufian.UserActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    fun signUpFirebase(email: String, password: String, activity: RegisterActivity) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                Toast.makeText(activity, "Başarıyla Kayıt Olundu!", Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, UserActivity::class.java)
                activity.startActivity(intent)
                activity.finishAffinity()
                activity.finish()
            }.addOnFailureListener {
                Toast.makeText(activity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        } else Toast.makeText(activity, "Lütfen Bilgileri Eksiksiz Girin!", Toast.LENGTH_SHORT)
            .show()
    }

    fun addUser(userName: String, userSurname: String, phone: String, email: String) {
        val userMap = hashMapOf<String, Any>()
        userMap.put("userName", userName)
        userMap.put("userSurname", userSurname)
        userMap.put("phone", phone)
        userMap.put("email", email)
        firestore.collection("Users").add(userMap).addOnSuccessListener {

        }
    }

}