package es.proyect.besocial.domain.usecases.auth

import es.proyect.besocial.domain.model.User
import es.proyect.besocial.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(val repository: AuthRepository) {
    suspend operator fun invoke(user: User) = repository.singUp(user)
}