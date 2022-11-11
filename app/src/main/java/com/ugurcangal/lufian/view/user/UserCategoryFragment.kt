package com.ugurcangal.lufian.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.FirebaseInstance.firestore
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentUserCategoryBinding
import com.ugurcangal.lufian.view.adapter.UserHomeAdapter
import com.ugurcangal.lufian.viewmodel.user.UserCategoryViewModel


class UserCategoryFragment : BaseFragment<FragmentUserCategoryBinding,UserCategoryViewModel>(FragmentUserCategoryBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categorySweatBtn.setOnClickListener {
            val action = UserCategoryFragmentDirections.actionUserCategoryFragmentToUserProductByCategoryFragment("Sweatshirt")
            Navigation.findNavController(it).navigate(action)
        }

        binding.categoryKazakBtn.setOnClickListener {
            val action = UserCategoryFragmentDirections.actionUserCategoryFragmentToUserProductByCategoryFragment("Kazak")
            Navigation.findNavController(it).navigate(action)
        }

        binding.categoryMontBtn.setOnClickListener {
            val action = UserCategoryFragmentDirections.actionUserCategoryFragmentToUserProductByCategoryFragment("Mont")
            Navigation.findNavController(it).navigate(action)
        }

        binding.categoryTshirtBtn.setOnClickListener {
            val action = UserCategoryFragmentDirections.actionUserCategoryFragmentToUserProductByCategoryFragment("T-Shirt")
            Navigation.findNavController(it).navigate(action)
        }

    }



    override fun getViewModel(): Class<UserCategoryViewModel> = UserCategoryViewModel::class.java

}