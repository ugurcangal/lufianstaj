package com.ugurcangal.lufian.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentUserProductBinding
import com.ugurcangal.lufian.viewmodel.user.UserProductViewModel


class UserProductFragment : BaseFragment<FragmentUserProductBinding,UserProductViewModel>(FragmentUserProductBinding::inflate) {

    private lateinit var productId : String
    private val firestore = Firebase.firestore
    private val auth = Firebase.auth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            productId = UserProductFragmentArgs.fromBundle(it).productID
        }

        viewModel.getBasket(binding, productId)
        viewModel.getProduct(binding, productId, view)
        viewModel.favoriteControl(binding, productId)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.favoriteButton.setOnClickListener {
            viewModel.addFavorite(productId, it)
        }

        binding.favoriteDeleteButton.setOnClickListener {
            viewModel.deleteFavorite(binding, productId, it)
        }

        binding.addToBasketBtn.setOnClickListener {
            viewModel.addToBasket(productId,binding)
        }


    }


    override fun getViewModel(): Class<UserProductViewModel> = UserProductViewModel::class.java


}