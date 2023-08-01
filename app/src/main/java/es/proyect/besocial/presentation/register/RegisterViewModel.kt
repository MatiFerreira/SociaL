package es.proyect.besocial.presentation.register

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseUser
import es.proyect.besocial.domain.model.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor() :
    ViewModel() {
    //aqui donde guardamos los datos de la aplicacion
    private val _email = mutableStateOf("")
    private val _password = mutableStateOf("")
    private val _nameUser = mutableStateOf("")
    private val _isRegisterEnable = mutableStateOf(false)
    private val _registerFlow = MutableStateFlow<Response<FirebaseUser>?>(null)

    //Almacenamos los datos que recibimos de fuera.
    val email: MutableState<String> = _email
    val password: MutableState<String> = _password
    val userName: MutableState<String> = _nameUser
    val registerEnable: MutableState<Boolean> = _isRegisterEnable
    val regiterFlow: StateFlow<Response<FirebaseUser>?> = _registerFlow

    fun CheckInfo(email: String, password: String, nickname: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length > 6 && nickname.length > 5
    }

    fun isRegister(navcontroller: NavHostController) {
        viewModelScope.launch {
        }
    }

    fun updateDataRegister(email: String, password: String, nickname: String) {
        _email.value = email
        _password.value = password
        _nameUser.value = nickname
        _isRegisterEnable.value = CheckInfo(email, password, nickname)
    }
}