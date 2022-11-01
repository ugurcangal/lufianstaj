package com.ugurcangal.lufian.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentUserHomeBinding
import com.ugurcangal.lufian.view.adapter.UserHomeAdapter
import com.ugurcangal.lufian.viewmodel.user.UserHomeViewModel


class UserHomeFragment : BaseFragment<FragmentUserHomeBinding,UserHomeViewModel>(FragmentUserHomeBinding::inflate) {

    private lateinit var userHomeAdapter: UserHomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProduct()
        userHomeAdapter = UserHomeAdapter()
        observeProductList()


        binding.userHomeRecycler.layoutManager = GridLayoutManager(context,2,
            GridLayoutManager.VERTICAL,false)
        binding.userHomeRecycler.adapter = userHomeAdapter

    }

    private fun observeProductList(){
        viewModel.observeProductList().observe(viewLifecycleOwner){
            userHomeAdapter.differ.submitList(it)

        }
    }

    override fun getViewModel(): Class<UserHomeViewModel> = UserHomeViewModel::class.java

}