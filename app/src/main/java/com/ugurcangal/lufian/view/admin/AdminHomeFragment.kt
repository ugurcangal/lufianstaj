package com.ugurcangal.lufian.view.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentAdminHomeBinding
import com.ugurcangal.lufian.viewmodel.admin.AdminHomeViewModel


class AdminHomeFragment : BaseFragment<FragmentAdminHomeBinding,AdminHomeViewModel>(FragmentAdminHomeBinding::inflate) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_home, container, false)
    }

    override fun getViewModel(): Class<AdminHomeViewModel> = AdminHomeViewModel::class.java

}