package com.example.corridafacil.domain.services.AuthenticationFirebaseSevice.Email

import com.example.corridafacil.domain.services.AuthenticationFirebaseSevice.FirebaseAuthentication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationEmailFirebaseServiceImpl  @Inject constructor() {

    private var firebaseAuth = FirebaseAuthentication.getInstanceFirebaseAuth()


    fun userAuthenticated(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun logout() {
        firebaseAuth.signOut()
    }


    suspend fun createNewAccountEmailPassword(emailUsuario: String, password: String): String {
        val firebaseAuth =  FirebaseAuthentication.getInstanceFirebaseAuth()
        return firebaseAuth.createUserWithEmailAndPassword(emailUsuario,password).await().user?.uid.toString()
    }


    suspend fun sendEmailVerification(): Void? {
        return  firebaseAuth.currentUser!!.sendEmailVerification().await()
    }


    suspend fun singInEmailAndPassword(emailUsuario: String, password: String): String {
        return firebaseAuth.signInWithEmailAndPassword(emailUsuario,password).await().user?.uid.toString()
    }

    fun sendPasswordResetEmail(email: String): Boolean {
        return firebaseAuth.sendPasswordResetEmail(email).isComplete
    }


}
