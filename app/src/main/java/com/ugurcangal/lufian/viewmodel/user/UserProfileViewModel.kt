package com.ugurcangal.lufian.viewmodel.user

import android.content.Context
import android.widget.Toast
import com.ugurcangal.lufian.BaseViewModel
import com.ugurcangal.lufian.databinding.FragmentUserProfileBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor() : BaseViewModel() {


    fun getUserProfileInfo(binding: FragmentUserProfileBinding){
        firestore.collection("Users").document(auth.currentUser!!.email.toString()).addSnapshotListener { value, error ->
            value?.let {
                binding.nameEditText.setText(it.get("userName") as String)
                binding.editTextSurname.setText(it.get("userSurname") as String)
                binding.editTextPhone.setText(it.get("phone") as String)
                binding.editTextEmail.setText(it.get("email") as String)
                if (it.data?.get("address") != null){
                    binding.editTextAdres.setText(it.get("address") as String)
                }else{
                    binding.editTextAdres.text?.clear()
                }
            }
        }
    }

    fun updateUserProfileInfo(binding: FragmentUserProfileBinding,context: Context){
        if (!binding.editTextAdres.text.isNullOrEmpty() && !binding.editTextAdres.text.isNullOrBlank()){
            firestore.collection("Users").document(auth.currentUser!!.email.toString()).update("address",binding.editTextAdres.text.toString().trim()).addOnSuccessListener {
                Toast.makeText(context, "Bilgiler Başarıyla Güncellendi.", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Lütfen Adres Bilgilerinizi Doğru Şekilde Girin!", Toast.LENGTH_SHORT).show()
        }
    }

}