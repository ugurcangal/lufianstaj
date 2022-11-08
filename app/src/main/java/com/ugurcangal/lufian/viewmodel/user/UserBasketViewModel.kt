package com.ugurcangal.lufian.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugurcangal.lufian.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserBasketViewModel @Inject constructor() : BaseViewModel() {

    var basketList = MutableLiveData<ArrayList<HashMap<String,String>>>()

    fun getBasket(){
        firestore.collection("Basket").document(auth.currentUser!!.email.toString()).addSnapshotListener { value, error ->
            value?.let {
                if (it.data?.isNotEmpty() == true){
                    basketList.value = it.get("basket") as ArrayList<HashMap<String,String>>
                }
            }
        }
    }

    fun observeBasketList() : LiveData<ArrayList<HashMap<String,String>>>{
        return basketList
    }

}