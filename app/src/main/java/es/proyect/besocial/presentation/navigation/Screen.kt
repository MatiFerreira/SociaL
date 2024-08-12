package es.proyect.besocial.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("LoginScreen")
    object Register : Screen("RegisterScreen")
    object Main : Screen(
        "Main"
    )

    object Lost : Screen("LostPassword")
}

