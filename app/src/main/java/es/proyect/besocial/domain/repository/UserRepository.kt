package es.proyect.besocial.domain.repository

import es.proyect.besocial.domain.model.Response
import es.proyect.besocial.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun create(user: User): Response<Boolean>
    fun getUserEmail(email: String): Flow<User>
    suspend fun update(user: User): Response<Boolean>
}