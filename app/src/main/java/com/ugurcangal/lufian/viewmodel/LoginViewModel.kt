package com.ugurcangal.lufian.viewmodel

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.view.activity.AdminActivity
import com.ugurcangal.lufian.view.activity.LoginActivity
import com.ugurcangal.lufian.view.activity.UserActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val auth = Firebase.auth

    private val adminUser = "admin@lufian.com"
    private val adminPass = "lufian"


    fun signInFirebase(userEmail: String, password: String, activity: LoginActivity, view :View) {

        if (userEmail == adminUser && password == adminPass) {
            auth.signInWithEmailAndPassword(userEmail, password)
                .addOnSuccessListener {
                    Toast.makeText(activity, "Başarıyla Giriş Yapıldı!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, AdminActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()

                }.addOnFailureListener {
                    Toast.makeText(activity, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
        } else if (userEmail.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(userEmail, password)
                .addOnSuccessListener {
                    Toast.makeText(activity, "Başarıyla Giriş Yapıldı!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, UserActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()

                }.addOnFailureListener {
                    Snackbar.make(activity,view,it.localizedMessage,Snackbar.LENGTH_SHORT).show()
//                    Toast.makeText(activity, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
        } else Toast.makeText(activity, "Lütfen Önce Bilgilerinizi Girin!", Toast.LENGTH_SHORT).show()
    }




}