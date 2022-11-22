package com.ugurcangal.lufian.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugurcangal.lufian.FirebaseInstance.auth
import com.ugurcangal.lufian.FirebaseInstance.firestore
import com.ugurcangal.lufian.databinding.UserBasketItemBinding
import com.ugurcangal.lufian.view.user.UserBasketFragmentDirections

class UserBasketAdapter(var basketList : ArrayList<HashMap<String,String>>) : RecyclerView.Adapter<UserBasketAdapter.BasketAdapterViewHolder>() {

    var availableList = ArrayList<HashMap<String,String>>()

    class BasketAdapterViewHolder(val binding: UserBasketItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapterViewHolder {
        val binding = UserBasketItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BasketAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketAdapterViewHolder, position: Int) {
        val product = basketList[position]
        val item = holder.binding
        val piece = basketList[position].get("piece").toString().toInt()
        val price = product.get("price").toString().toInt()


        item.pieceTxt.text = piece.toString()
        item.basketProductNameTV.text = product.get("name")?.replaceFirstChar {
            it.uppercase()
        }

        item.basketProductPriceTV.text = (price * piece).toString() + " TL"
        item.basketProductSizeTV.text = "Beden: " + product.get("size")
        Glide.with(holder.itemView.context).load(product.get("downloadUrl")).into(item.basketIV)

        holder.itemView.setOnClickListener {
            val action = UserBasketFragmentDirections.actionUserBasketFragmentToUserProductFragment(product.get("id").toString())
            Navigation.findNavController(it).navigate(action)
        }

        if (item.pieceTxt.text == "1"){
            item.pieceRemoveBtn.isEnabled = false
        }


        firestore.collection("Basket").document(auth.currentUser!!.email.toString()).addSnapshotListener { value, error ->
            value?.let {
                if (it.data?.isNotEmpty() == true){
                    availableList = it["basket"] as ArrayList<HashMap<String, String>>
                }
            }
        }

        item.deleteButton.setOnClickListener {
            availableList.remove(product)
            firestore.collection("Basket").document(auth.currentUser!!.email.toString()).update("basket",availableList)
        }

        item.pieceAddBtn.setOnClickListener {
            val newPiece = piece!!.toInt() + 1
            product.put("piece",newPiece.toString())
            availableList.set(position, product)
            firestore.collection("Basket").document(auth.currentUser!!.email.toString()).update("basket",availableList)
        }

        item.pieceRemoveBtn.setOnClickListener {
            val newPiece = piece!!.toInt() - 1
            product.put("piece",newPiece.toString())
            availableList.set(position, product)
            firestore.collection("Basket").document(auth.currentUser!!.email.toString()).update("basket",availableList)
        }




    }

    override fun getItemCount(): Int {
        return basketList.size
    }
}