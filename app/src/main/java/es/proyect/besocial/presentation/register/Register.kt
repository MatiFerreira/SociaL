package es.proyect.besocial.presentation.register


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import es.proyect.besocial.presentation.components.login_component.TopBar
import es.proyect.besocial.presentation.components.RegisterComponent.BodyR
import es.proyect.besocial.presentation.components.RegisterComponent.HeaderR


@Composable
fun RegisterView(navigation: NavHostController, viewModel: RegisterViewModel = hiltViewModel()) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TopBar(navigation)
        HeaderR()
        BodyR(navigation, viewModel)
    }
}



