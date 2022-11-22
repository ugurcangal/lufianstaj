package com.ugurcangal.lufian.view.user

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentUserBasketBinding
import com.ugurcangal.lufian.view.adapter.UserBasketAdapter
import com.ugurcangal.lufian.viewmodel.user.UserBasketViewModel


class UserBasketFragment : BaseFragment<FragmentUserBasketBinding,UserBasketViewModel>(FragmentUserBasketBinding::inflate) {

    private lateinit var userBasketAdapter : UserBasketAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTotalPrice(binding)
        viewModel.getBasket()
        observeBasketList()
        binding.completeOrder.setOnClickListener {
            viewModel.completeOrder(requireContext(),it)
        }

    }

    private fun observeBasketList(){
        viewModel.observeBasketList().observe(viewLifecycleOwner){
            userBasketAdapter = UserBasketAdapter(it)
            binding.userBasketRV.layoutManager = LinearLayoutManager(context)
            binding.userBasketRV.adapter = userBasketAdapter
            userBasketAdapter.notifyDataSetChanged()

        }
    }

    override fun getViewModel(): Class<UserBasketViewModel> = UserBasketViewModel::class.java
}