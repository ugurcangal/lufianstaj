package com.ugurcangal.lufian.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentUserHomeBinding
import com.ugurcangal.lufian.viewmodel.user.UserHomeViewModel


class UserHomeFragment : BaseFragment<FragmentUserHomeBinding,UserHomeViewModel>(FragmentUserHomeBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home, container, false)

    }

    override fun getViewModel(): Class<UserHomeViewModel> = UserHomeViewModel::class.java

}