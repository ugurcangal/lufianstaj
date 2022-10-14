package com.ugurcangal.lufian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.databinding.ActivityAdminBinding
import com.ugurcangal.lufian.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val auth = Firebase.auth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        binding.registerButton.setOnClickListener {
            signUp()
        }

    }

    private fun signUp() {
        val name = binding.editTextName.text
        val surname = binding.editTextSurname.text
        val phone = binding.editTextPhone.text
        val email = binding.editTextTextEmail.text.toString()
        val password = binding.editTextTextPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && phone.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        } else Toast.makeText(this,"Lütfen Bilgileri Eksiksiz Girin!",Toast.LENGTH_SHORT).show()
    }

}
