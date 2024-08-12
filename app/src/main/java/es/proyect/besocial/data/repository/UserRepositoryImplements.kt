package es.proyect.besocial.data.repository

import com.google.firebase.firestore.CollectionReference
import es.proyect.besocial.domain.model.Response
import es.proyect.besocial.domain.model.User
import es.proyect.besocial.domain.repository.UserRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImplements @Inject constructor(private val userRef: CollectionReference) :
    UserRepository {
    override suspend fun create(user: User): Response<Boolean> {
        return try {
            userRef.document(user.id).set(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun getUserEmail(email: String): Flow<User> = callbackFlow {
        val snapshotListener = userRef.document(email).addSnapshotListener { snapshotFlow, e ->
            val user = snapshotFlow?.toObject(User::class.java) ?: User()
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = HashMap()
            map["nickName"] = user.nickName
            map["image"] = user.image
            userRef.document(user.id).update(map).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}
