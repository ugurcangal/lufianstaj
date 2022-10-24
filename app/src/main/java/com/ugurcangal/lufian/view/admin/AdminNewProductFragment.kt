package com.ugurcangal.lufian.view.admin

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.fragment.findNavController
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentAdminNewProductBinding
import com.ugurcangal.lufian.viewmodel.admin.AdminNewProductViewModel
import java.util.*


class AdminNewProductFragment :
    BaseFragment<FragmentAdminNewProductBinding, AdminNewProductViewModel>(
        FragmentAdminNewProductBinding::inflate
    ) {


    private var selectedImage: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 100)


        //Color
        ArrayAdapter.createFromResource(requireContext(),R.array.color_array, androidx.transition.R.layout.support_simple_spinner_dropdown_item).also {
            it.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)
            binding.colorSpinner.adapter = it
        }

        //Category
        ArrayAdapter.createFromResource(requireContext(),R.array.category_array, androidx.transition.R.layout.support_simple_spinner_dropdown_item).also {
            it.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)
            binding.categorySpinner.adapter = it
        }



        binding.addImageview.setOnClickListener {
            selectedImageLauncher.launch("image/*")

        }
        binding.uploadButton.setOnClickListener {
            val name = binding.nameEditText.text
            val price = binding.priceEditText.text
            val color = binding.colorSpinner.selectedItem.toString()
            val category = binding.categorySpinner.selectedItem.toString()
            if (selectedImage != null && name.toString().isNotEmpty() && price.toString().isNotEmpty() && color.isNotEmpty() && category.isNotEmpty()){
                viewModel.uploadProduct(requireContext(),selectedImage,name.toString(),price.toString(),color, category)
                binding.addImageview.setImageResource(R.drawable.ic_baseline_add_a_photo_24)
                name!!.clear()
                price!!.clear()
                findNavController().navigate(R.id.action_adminNewProductFragment_to_adminHomeFragment)
            }


        }

    }

    override fun getViewModel(): Class<AdminNewProductViewModel> =
        AdminNewProductViewModel::class.java

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requestPermissions(arrayOf(permission), requestCode)
        } else {
            Toast.makeText(requireContext(), "Permission already granted", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private val selectedImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.addImageview.setImageURI(it)
            selectedImage = it
        }


}