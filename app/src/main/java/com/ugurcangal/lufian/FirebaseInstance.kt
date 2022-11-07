package com.ugurcangal.lufian

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

object FirebaseInstance {

    val firestore = Firebase.firestore
    val auth = Firebase.auth
    val storage = Firebase.storage

}