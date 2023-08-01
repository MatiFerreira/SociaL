package es.proyect.besocial.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRegistrar
import com.google.firebase.auth.FirebaseUser
import es.proyect.besocial.domain.model.Response
import es.proyect.besocial.domain.model.User
import es.proyect.besocial.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImplements @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthRepository {
    override val currentUser: FirebaseUser? = firebaseAuth.currentUser


    override suspend fun login(email: String, password: String): Response<FirebaseUser> {
        return try {
            val signIn = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Response.Success(signIn.user!!)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun logOut() {
        firebaseAuth.signOut()
    }

    override suspend fun singUp(user: User): Response<FirebaseUser> {
        return try {
            val result =
                firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
            Response.Success(result.user!!)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }


}