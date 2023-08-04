package es.proyect.besocial.presentation.register

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import es.proyect.besocial.domain.model.Response
import es.proyect.besocial.domain.model.User
import es.proyect.besocial.domain.usecases.auth.AuthUseCase
import es.proyect.besocial.domain.usecases.users.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase
) :
    ViewModel() {
    //aqui donde guardamos los datos de la aplicacion
    private var _email by mutableStateOf("")
    private var _password by mutableStateOf("")
    private var _nameUser by mutableStateOf("")
    private val _registerFlow = MutableStateFlow<Response<FirebaseUser>?>(null)

    //Almacenamos los datos que recibimos de fuera.
    var email by mutableStateOf(_email)
    var password by mutableStateOf(_password)
    var userName by mutableStateOf(_nameUser)
    var regiterFlow: StateFlow<Response<FirebaseUser>?> = _registerFlow

    fun CheckInfo(email: String, password: String, nickname: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length > 6 && nickname.length > 5
    }

    fun isRegister(user: User) {
        viewModelScope.launch {
            _registerFlow.value = Response.Loading
            val results = authUseCase.signUp(user)
            _registerFlow.value = results
        }
    }

    private var user = User()
    fun signUp() {
        user.email = email
        user.password = password
        user.nickName = userName
        isRegister(user)
    }


    fun createUser() =
        viewModelScope.launch {
            user.password = " "
            user.id = user.email
            userUseCase.create(user)
        }

}