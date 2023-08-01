package es.proyect.besocial.domain.usecases.auth

data class AuthUseCase(
    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val loginOut: LogOut,
    val signUp: SignUp
)
