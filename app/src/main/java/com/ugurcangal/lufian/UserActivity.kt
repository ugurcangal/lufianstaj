package com.ugurcangal.lufian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ugurcangal.lufian.databinding.ActivityUserBinding
import com.ugurcangal.lufian.view.user.UserBasketFragment
import com.ugurcangal.lufian.view.user.UserHomeFragment
import com.ugurcangal.lufian.view.user.UserProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        replaceFragment(UserHomeFragment())

        binding.userBottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.userHome -> replaceFragment(UserHomeFragment())
                R.id.userBasket -> replaceFragment(UserBasketFragment())
                R.id.userProfile -> replaceFragment(UserProfileFragment())
                else -> {

                }
            }
                true
            }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }
}