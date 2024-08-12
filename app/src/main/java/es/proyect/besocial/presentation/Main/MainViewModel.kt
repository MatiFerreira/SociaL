package es.proyect.besocial.presentation.Main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.proyect.besocial.domain.model.User
import es.proyect.besocial.domain.usecases.auth.AuthUseCase
import es.proyect.besocial.domain.usecases.users.UserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {
    var userdata by mutableStateOf(User())
        private set

    var currentUser = authUseCase.getCurrentUser()!!.email
    fun logOut() {
        authUseCase.loginOut()
    }

    fun getUserData() {
        viewModelScope.launch {
            userUseCase.getEmailId(currentUser!!).collect() { data -> userdata = data }
        }
    }

    init {
        getUserData()
    }
}