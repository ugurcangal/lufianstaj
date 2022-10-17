package com.ugurcangal.lufian.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.AdminActivity
import com.ugurcangal.lufian.LoginActivity
import com.ugurcangal.lufian.UserActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val auth = Firebase.auth

    private val adminUser = "admin@lufian.com"
    private val adminPass = "lufian"


    fun signInFirebase(userEmail: String, password: String, activity: LoginActivity) {

        if (userEmail == adminUser && password == adminPass) {
            auth.signInWithEmailAndPassword(userEmail, password)
                .addOnSuccessListener {
                    Toast.makeText(activity, it.user.toString(), Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, AdminActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()

                }.addOnFailureListener {
                    Toast.makeText(activity, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
        } else if (userEmail.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(userEmail, password)
                .addOnSuccessListener {
                    Toast.makeText(activity, it.user.toString(), Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, UserActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()

                }.addOnFailureListener {
                    Toast.makeText(activity, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
        } else Toast.makeText(activity, "Lütfen Önce Bilgilerinizi Girin!", Toast.LENGTH_SHORT).show()
    }




}