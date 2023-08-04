package es.proyect.besocial.presentation.Login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import es.proyect.besocial.domain.model.Response
import es.proyect.besocial.domain.usecases.auth.AuthUseCase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private var _email by mutableStateOf("")
    private var _password by mutableStateOf("")
    private var _isLoginEnable by mutableStateOf(false)
    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)


    //ALMACENAMOS LOS DATOS QUE RECOGERMOS
    //Guardo el valor que le pasa por parametro para poder accerder
    var email by mutableStateOf(_email)
    var password by mutableStateOf(_password)
    val loadingEnable by mutableStateOf(_isLoginEnable)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow

    //Compruebo si estos datos son correctos
    fun CheckInfo(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
    }

    //Si esta bien entonces vamos a iniciar sesión
    fun isLoginApp() {
        viewModelScope.launch {
            _loginFlow.value = Response.Loading
            val signIn = authUseCase.login(email, password)
            _loginFlow.value = signIn
        }
    }

    val currentUser = authUseCase.getCurrentUser()

    init {
        if (currentUser != null) {
            //is
            _loginFlow.value = Response.Success(currentUser)
        }
    }
}