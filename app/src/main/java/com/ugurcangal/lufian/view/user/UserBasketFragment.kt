package com.ugurcangal.lufian.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentUserBasketBinding
import com.ugurcangal.lufian.viewmodel.user.UserBasketViewModel


class UserBasketFragment : BaseFragment<FragmentUserBasketBinding,UserBasketViewModel>(FragmentUserBasketBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_basket, container, false)
    }

    override fun getViewModel(): Class<UserBasketViewModel> = UserBasketViewModel::class.java
}