package es.proyect.besocial.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRegistrar
import com.google.firebase.auth.FirebaseUser
import es.proyect.besocial.domain.model.Response
import es.proyect.besocial.domain.model.User

interface AuthRepository {

    val currentUser: FirebaseUser?

    suspend fun login(email: String, password: String): Response<FirebaseUser>
    fun logOut()

    suspend fun singUp(user: User):Response<FirebaseUser>
}