package es.proyect.besocial.presentation.Login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _isLoginEnable = MutableLiveData<Boolean>()

    //ALMACENAMOS LOS DATOS QUE RECOGERMOS
    //Guardo el valor que le pasa por parametro para poder accerder
    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val loadingEnable: LiveData<Boolean> = _isLoginEnable

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
        if (loadingEnable.value == true) {
            viewModelScope.launch {
                //Lanzamos la nuevo modelo de vista.
                Log.i("Hola", "VALORES : ${email.value}, ${password.value}")
            }
        } else {
            Log.i("Hola", "NO FUNCIONA!!!!!!!!!!!")

        }
    }
}