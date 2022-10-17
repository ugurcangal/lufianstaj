package com.ugurcangal.lufian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.databinding.ActivityAdminBinding
import com.ugurcangal.lufian.databinding.ActivityRegisterBinding
import com.ugurcangal.lufian.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]




        binding.registerButton.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val surname = binding.editTextSurname.text.toString()
            val phone = binding.editTextPhone.text.toString()
            val email = binding.editTextTextEmail.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            viewModel.signUpFirebase(email,password,this)
            viewModel.addUser(name,surname,phone,email)
        }

    }


}
