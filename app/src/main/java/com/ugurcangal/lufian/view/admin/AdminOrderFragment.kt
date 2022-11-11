package com.ugurcangal.lufian.view.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentAdminOrderBinding
import com.ugurcangal.lufian.viewmodel.admin.AdminOrderViewModel


class AdminOrderFragment : BaseFragment<FragmentAdminOrderBinding,AdminOrderViewModel>(FragmentAdminOrderBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun getViewModel(): Class<AdminOrderViewModel> = AdminOrderViewModel::class.java

}