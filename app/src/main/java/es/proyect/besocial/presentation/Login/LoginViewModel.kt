package es.proyect.besocial.presentation.Login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    private val _email = mutableStateOf("")
    private val _password = mutableStateOf("")
    private val _isLoginEnable = mutableStateOf(false)
    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)


    //ALMACENAMOS LOS DATOS QUE RECOGERMOS
    //Guardo el valor que le pasa por parametro para poder accerder
    val email: MutableState<String> = _email
    val password: MutableState<String> = _password
    val loadingEnable: MutableState<Boolean> = _isLoginEnable
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow

    //COMPROBAR SI ESTA CORRECTO
    fun onLoginUpdate(email: String, password: String) {
        //Recogemos los valores que se pasa por metodos
        _email.value = email
        _password.value = password
        _isLoginEnable.value = CheckInfo(email, password)
    }

    //Compruebo si estos datos son correctos
    fun CheckInfo(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
    }

    //Si esta bien entonces vamos a iniciar sesión
    fun isLoginApp() {
        viewModelScope.launch {
            _loginFlow.value = Response.Loading
            val signIn = authUseCase.login(email.value, password.value)
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