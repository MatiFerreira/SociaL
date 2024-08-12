package es.proyect.besocial.domain.model

data class User(
    var email: String = "",
    var nickName: String = "",
    var password: String = "",
    var id: String = "",
    var image: String = ""
)