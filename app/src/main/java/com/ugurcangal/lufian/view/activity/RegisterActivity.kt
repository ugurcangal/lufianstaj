package com.ugurcangal.lufian.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
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
            viewModel.signUpFirebase(name,surname,phone,email,password,this)

        }

    }


}
