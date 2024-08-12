package es.proyect.besocial.domain.usecases.users

import es.proyect.besocial.domain.model.User
import es.proyect.besocial.domain.repository.UserRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: User) = repository.create(user)
}