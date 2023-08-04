package es.proyect.besocial.domain.usecases.users

import es.proyect.besocial.domain.repository.UserRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(email: String) = repository.getUserEmail(email)
}