package com.ugurcangal.lufian.view.user

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.databinding.FragmentUserProductByCategoryBinding
import com.ugurcangal.lufian.view.adapter.UserProductsByCategoryAdapter
import com.ugurcangal.lufian.viewmodel.user.UserProductsByCategoryViewModel

class UserProductsByCategoryFragment : BaseFragment<FragmentUserProductByCategoryBinding,UserProductsByCategoryViewModel>(FragmentUserProductByCategoryBinding::inflate) {

    private lateinit var categoryName : String
    private lateinit var userProductsByCategoryAdapter: UserProductsByCategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            categoryName = UserProductsByCategoryFragmentArgs.fromBundle(it).categoryName
        }

        userProductsByCategoryAdapter = UserProductsByCategoryAdapter()
        viewModel.getProductForCategory(categoryName)
        observeProductList()
        binding.title.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun observeProductList(){
        viewModel.observeProductList().observe(viewLifecycleOwner){
            userProductsByCategoryAdapter.differ.submitList(it)
            binding.userProductsByCategoryRecycler.layoutManager = GridLayoutManager(context,2,
                GridLayoutManager.VERTICAL,false)
            binding.userProductsByCategoryRecycler.adapter = userProductsByCategoryAdapter
        }
    }


    override fun getViewModel(): Class<UserProductsByCategoryViewModel> = UserProductsByCategoryViewModel::class.java
}