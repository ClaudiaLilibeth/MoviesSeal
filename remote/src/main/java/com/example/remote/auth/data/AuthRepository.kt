package com.example.remote.auth.data

import com.google.firebase.auth.FirebaseUser

//#firebase 2 Firebase events as interface
interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}