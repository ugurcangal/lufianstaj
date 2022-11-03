package com.ugurcangal.lufian.view.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentUserProfileBinding
import com.ugurcangal.lufian.view.activity.LoginActivity
import com.ugurcangal.lufian.view.activity.UserActivity
import com.ugurcangal.lufian.viewmodel.user.UserProfileViewModel


class UserProfileFragment : BaseFragment<FragmentUserProfileBinding,UserProfileViewModel>(FragmentUserProfileBinding::inflate) {

    val firestore = Firebase.firestore
    val auth = Firebase.auth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutBtn.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()

        }

        viewModel.getUserProfileInfo(binding)

    }

    override fun getViewModel(): Class<UserProfileViewModel> = UserProfileViewModel::class.java

}