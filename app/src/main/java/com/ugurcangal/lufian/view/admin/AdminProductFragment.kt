package com.ugurcangal.lufian.view.admin

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentAdminProductBinding
import com.ugurcangal.lufian.model.Product
import com.ugurcangal.lufian.view.adapter.AdminHomeAdapter
import com.ugurcangal.lufian.viewmodel.admin.AdminProductViewModel
import java.util.UUID


class AdminProductFragment : BaseFragment<FragmentAdminProductBinding,AdminProductViewModel>(FragmentAdminProductBinding::inflate) {

    private lateinit var productAdapter : AdminHomeAdapter
    var productList : ArrayList<Product> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Category
        ArrayAdapter.createFromResource(requireContext(),R.array.category_array, androidx.transition.R.layout.support_simple_spinner_dropdown_item).also {
            it.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)
            binding.productCategorySpinner.adapter = it
        }

        productAdapter = AdminHomeAdapter()
        observeProductList()


        binding.productCategorySpinner.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val selectedItem = p0?.getItemAtPosition(p2).toString()
                viewModel.getProductForCategory(selectedItem)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }


        binding.productRecycler.layoutManager = GridLayoutManager(context,2,
            GridLayoutManager.VERTICAL,false)
        binding.productRecycler.adapter = productAdapter


    }

    override fun getViewModel(): Class<AdminProductViewModel> = AdminProductViewModel::class.java


    private fun observeProductList(){
        viewModel.observeProductList().observe(viewLifecycleOwner){
            productAdapter.differ.submitList(it)
            productList = it

        }
    }

}