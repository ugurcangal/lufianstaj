package com.ugurcangal.lufian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.databinding.ActivityAdminBinding
import com.ugurcangal.lufian.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val auth = Firebase.auth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val name = binding.editTextName
        val surname = binding.editTextSurname
        val phone = binding.editTextPhone



        binding.registerButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp(){
        val email = binding.editTextTextEmail.toString()
        val password = binding.editTextTextPassword.toString()


            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent = Intent(this,UserActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
            }

    }
}