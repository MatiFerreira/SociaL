package es.proyect.besocial.domain.usecases.auth

import es.proyect.besocial.domain.repository.AuthRepository
import javax.inject.Inject

class LogOut @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.logOut()
}