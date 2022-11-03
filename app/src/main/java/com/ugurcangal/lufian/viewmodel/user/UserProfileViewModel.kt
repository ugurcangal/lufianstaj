package com.ugurcangal.lufian.viewmodel.user

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.databinding.FragmentUserProfileBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor() : ViewModel() {

    val firestore = Firebase.firestore
    val auth = Firebase.auth


    fun getUserProfileInfo(binding: FragmentUserProfileBinding){
        firestore.collection("Users").document(auth.currentUser!!.email.toString()).addSnapshotListener { value, error ->
            value?.let {
                binding.nameEditText.setText(it.get("userName") as String)
                binding.editTextSurname.setText(it.get("userSurname") as String)
                binding.editTextPhone.setText(it.get("phone") as String)
                binding.editTextEmail.setText(it.get("email") as String)
            }
        }

    }

}