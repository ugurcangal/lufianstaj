package com.ugurcangal.lufian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.databinding.ActivityMainBinding
import com.ugurcangal.lufian.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val auth = Firebase.auth
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]



        auth.currentUser?.let {
//            if (it.email == "admin@lufian.com"){
//                val intent = Intent(this, AdminActivity::class.java)
//                startActivity(intent)
//                finish()
//            }else {
//                val intent = Intent(this, UserActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
        }

        binding.girisButton.setOnClickListener {
            viewModel.signInFirebase(binding.editTextEmail.text.toString(),binding.editTextTextPassword.text.toString(),this)
        }


        binding.goToRegisterTV.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


}