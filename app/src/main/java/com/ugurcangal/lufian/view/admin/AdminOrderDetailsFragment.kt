package com.ugurcangal.lufian.view.admin

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.databinding.FragmentAdminOrderDetailsBinding
import com.ugurcangal.lufian.view.adapter.AdminOrderDetailsAdapter
import com.ugurcangal.lufian.view.adapter.UserBasketAdapter
import com.ugurcangal.lufian.viewmodel.admin.AdminOrderDetailsViewModel


class AdminOrderDetailsFragment : BaseFragment<FragmentAdminOrderDetailsBinding,AdminOrderDetailsViewModel>(FragmentAdminOrderDetailsBinding::inflate) {

    private lateinit var documentId : String
    private lateinit var adminOrdersDetailsAdapter: AdminOrderDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            documentId = AdminOrderDetailsFragmentArgs.fromBundle(it).documentId
        }

        viewModel.getOrderDetails(documentId,binding)
        observeBasketList()


    }

    fun observeBasketList(){
        viewModel.observeBasketList().observe(viewLifecycleOwner){
            adminOrdersDetailsAdapter = AdminOrderDetailsAdapter(it)
            binding.adminOrderDetailsRV.layoutManager = LinearLayoutManager(context)
            binding.adminOrderDetailsRV.adapter = adminOrdersDetailsAdapter
            adminOrdersDetailsAdapter.notifyDataSetChanged()
        }
    }

    override fun getViewModel(): Class<AdminOrderDetailsViewModel> = AdminOrderDetailsViewModel::class.java

}