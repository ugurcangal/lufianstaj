package com.ugurcangal.lufian.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ugurcangal.lufian.BaseFragment
import com.ugurcangal.lufian.R
import com.ugurcangal.lufian.databinding.FragmentUserFavoritesBinding
import com.ugurcangal.lufian.model.Product
import com.ugurcangal.lufian.view.adapter.UserFavoriteAdapter
import com.ugurcangal.lufian.view.adapter.UserHomeAdapter
import com.ugurcangal.lufian.viewmodel.user.UserFavoritesViewModel


class UserFavoritesFragment : BaseFragment<FragmentUserFavoritesBinding,UserFavoritesViewModel>(FragmentUserFavoritesBinding::inflate) {

    private lateinit var userFavoriteAdapter: UserFavoriteAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getFavoriteProduct()
        observeProductList()

    }

    private fun observeProductList(){
        viewModel.observeProductList().observe(viewLifecycleOwner){
            userFavoriteAdapter = UserFavoriteAdapter(it)
            binding.userFavRecycler.layoutManager = GridLayoutManager(context,2,
                GridLayoutManager.VERTICAL,false)
            binding.userFavRecycler.adapter = userFavoriteAdapter
            userFavoriteAdapter.notifyDataSetChanged()
        }
    }

    override fun getViewModel(): Class<UserFavoritesViewModel> = UserFavoritesViewModel::class.java

}