package es.proyect.besocial.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.proyect.besocial.presentation.Login.LoginView
import es.proyect.besocial.presentation.Login.LoginViewModel
import es.proyect.besocial.presentation.register.RegisterView

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
    }
}