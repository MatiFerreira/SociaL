package es.proyect.besocial.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.proyect.besocial.presentation.Login.LoginView
import es.proyect.besocial.presentation.LostPsswrd.MyLostPaswrdView
import es.proyect.besocial.presentation.Main.MainView
import es.proyect.besocial.presentation.register.RegisterView
import javax.inject.Inject

class NavegationScreenController @Inject constructor() : ViewModel() {

    @Composable
    fun AppNavegation(navigation: NavHostController) {
        NavHost(
            navController = navigation,
            startDestination = Screen.Login.route
        ) {
            composable(route = Screen.Login.route) {
                LoginView(navigation)
            }
            composable(route = Screen.Register.route) {
                RegisterView(navigation)
            }
            composable(route = Screen.Main.route) {
                MainView(navigation)
            }
            composable(route = Screen.Lost.route) {
                MyLostPaswrdView(navigation)
            }
        }
    }
}