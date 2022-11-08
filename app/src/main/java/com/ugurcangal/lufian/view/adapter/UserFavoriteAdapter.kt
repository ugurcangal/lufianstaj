package com.ugurcangal.lufian.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ugurcangal.lufian.databinding.UserProductItemBinding
import com.ugurcangal.lufian.model.Product
import com.ugurcangal.lufian.view.user.UserFavoritesFragmentDirections

class UserFavoriteAdapter(var favoriteList : ArrayList<Product>): RecyclerView.Adapter<UserFavoriteAdapter.UserFavoriteViewHolder>() {


    class UserFavoriteViewHolder(val binding: UserProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFavoriteViewHolder {
        val binding =
            UserProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserFavoriteViewHolder(binding)
    }

    /*
    private val diffUtil = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)

     */

    override fun onBindViewHolder(holder: UserFavoriteViewHolder, position: Int) {
        val product = favoriteList[position]
        val item = holder.binding
        val firestore = Firebase.firestore
        val auth = Firebase.auth
        item.productName.text = product.name
        item.productPrice.text = product.price + " " + "TL"
        Glide.with(holder.itemView.context).load(product.imageUrl).into(item.productImage)

        item.root.setOnClickListener {
            val action = UserFavoritesFragmentDirections.actionUserFavoritesFragmentToUserProductFragment(product.id)
            Navigation.findNavController(it).navigate(action)
        }



        var favorites = ArrayList<Any>()
        val favoritesMap = HashMap<String,Any>()
        firestore.collection("Favorites").document(auth.currentUser!!.email.toString()).addSnapshotListener { value, error ->
            value?.let {
                favorites = it.get("favorites") as ArrayList<Any>
                if (favorites.contains(product.id)){
                    item.favoriteButton.visibility = View.GONE
                    item.favoriteDeleteButton.visibility = View.VISIBLE
                }else{
                    item.favoriteButton.visibility = View.VISIBLE
                    item.favoriteDeleteButton.visibility = View.GONE
                }

            }
        }


        item.favoriteDeleteButton.setOnClickListener {
            favoriteList.clear()
            favorites.remove(product.id)
            favoritesMap.put("favorites",favorites)
            firestore.collection("Favorites").document(auth.currentUser!!.email.toString()).update(favoritesMap).addOnSuccessListener {
            }

        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }


}
