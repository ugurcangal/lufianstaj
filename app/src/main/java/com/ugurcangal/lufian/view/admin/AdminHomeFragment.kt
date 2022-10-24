package com.ugurcangal.lufian.view.admin

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.databinding.FragmentAdminHomeBinding
import com.ugurcangal.lufian.model.Product
import com.ugurcangal.lufian.view.adapter.AdminHomeAdapter
import com.ugurcangal.lufian.viewmodel.admin.AdminHomeViewModel


class AdminHomeFragment : BaseFragment<FragmentAdminHomeBinding,AdminHomeViewModel>(FragmentAdminHomeBinding::inflate) {

    private lateinit var productArrayList : ArrayList<Product>
    private lateinit var homeAdapter : AdminHomeAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProduct()
        productArrayList = ArrayList()
        observeProductList()
        homeAdapter = AdminHomeAdapter()

        binding.adminHomeRecycler.layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        binding.adminHomeRecycler.adapter = homeAdapter
    }

    override fun getViewModel(): Class<AdminHomeViewModel> = AdminHomeViewModel::class.java

    private fun observeProductList(){
        viewModel.observeProductList().observe(viewLifecycleOwner){
            homeAdapter.setList(it)
            homeAdapter.notifyDataSetChanged()
        }
    }

}