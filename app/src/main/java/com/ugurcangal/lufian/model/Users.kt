package com.ugurcangal.lufian.model

data class Users(
    val id : String,
    val userName : String,
    val userSurname : String,
    val email : String,
    val phone: String,
    val favorites: ArrayList<Product>
)
