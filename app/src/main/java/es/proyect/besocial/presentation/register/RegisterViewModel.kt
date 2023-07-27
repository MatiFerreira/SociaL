package es.proyect.besocial.presentation.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import es.proyect.besocial.presentation.navigation.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor() : ViewModel() {
    //aqui donde guardamos los datos de la aplicacion
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _nameUser = MutableLiveData<String>()
    private val _isRegisterEnable = MutableLiveData<Boolean>()

    //Almacenamos los datos que recibimos de fuera.
    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val userName: LiveData<String> = _nameUser
    val registerEnable: LiveData<Boolean> = _isRegisterEnable

    fun CheckInfo(email: String, password: String, nickname: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length > 6 && nickname.length > 5
    }

    fun isRegister(navcontroller: NavHostController) {
        if (registerEnable.value == true) {
            viewModelScope.launch {
                navcontroller.navigate(Screen.Login.route)
            }
        }
    }

    fun updateDataRegister(email: String, password: String, nickname: String) {
        _email.value = email
        _password.value = password
        _nameUser.value = nickname
        _isRegisterEnable.value = CheckInfo(email, password, nickname)
    }
}