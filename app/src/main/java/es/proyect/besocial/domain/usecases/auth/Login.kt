package es.proyect.besocial.domain.usecases.auth

import es.proyect.besocial.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String) =
        authRepository.login(email, password)

}