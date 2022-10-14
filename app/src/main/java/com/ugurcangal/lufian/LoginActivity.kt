package com.ugurcangal.lufian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adminUser = "admin"
        val adminPass = "lufian"

        val userEmail = binding.editTextEmail.text
        val password = binding.editTextTextPassword.text


        binding.girisButton.setOnClickListener {
            if (userEmail.toString() == adminUser && password.toString() == adminPass){
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
                finish()
            }else if (userEmail.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(userEmail.toString(),password.toString()).addOnSuccessListener {
                    val intent = Intent(this,UserActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this,it.localizedMessage,Toast.LENGTH_SHORT).show()
                }
            }else Toast.makeText(this,"Lütfen Giriş Bilgilerinizi Yazın",Toast.LENGTH_SHORT).show()
        }

        binding.goToRegisterTV.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}