package es.proyect.besocial.presentation.Main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.proyect.besocial.domain.usecases.auth.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    fun logOut() {
        authUseCase.loginOut()
    }
}