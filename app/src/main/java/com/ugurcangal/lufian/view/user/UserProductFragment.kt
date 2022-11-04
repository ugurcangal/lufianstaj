package com.ugurcangal.lufian.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentUserProductBinding
import com.ugurcangal.lufian.viewmodel.user.UserProductViewModel


class UserProductFragment : BaseFragment<FragmentUserProductBinding,UserProductViewModel>(FragmentUserProductBinding::inflate) {

    private lateinit var productId : String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            productId = UserProductFragmentArgs.fromBundle(it).productID
        }

        viewModel.getProduct(binding, productId, view)

    }


    override fun getViewModel(): Class<UserProductViewModel> = UserProductViewModel::class.java


}