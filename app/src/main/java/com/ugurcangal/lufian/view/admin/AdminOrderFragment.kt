package com.ugurcangal.lufian.view.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentAdminOrderBinding
import com.ugurcangal.lufian.view.adapter.AdminOrdersAdapter
import com.ugurcangal.lufian.viewmodel.admin.AdminOrderViewModel


class AdminOrderFragment : BaseFragment<FragmentAdminOrderBinding,AdminOrderViewModel>(FragmentAdminOrderBinding::inflate) {

    private lateinit var adminOrdersAdapter: AdminOrdersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getOrders()
        observeOrderList()

    }

    fun observeOrderList(){
        viewModel.observeOrderList().observe(viewLifecycleOwner){
            println(it)
            adminOrdersAdapter = AdminOrdersAdapter(it)
            binding.adminOrderRV.layoutManager = LinearLayoutManager(context)
            binding.adminOrderRV.adapter = adminOrdersAdapter
            adminOrdersAdapter.notifyDataSetChanged()

        }
    }


    override fun getViewModel(): Class<AdminOrderViewModel> = AdminOrderViewModel::class.java

}