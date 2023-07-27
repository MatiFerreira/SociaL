package es.proyect.besocial.presentation.Login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import es.proyect.besocial.presentation.Components.LoginComponent.BodyL
import es.proyect.besocial.presentation.Components.LoginComponent.BottomL
import es.proyect.besocial.presentation.Components.LoginComponent.Header
import es.proyect.besocial.presentation.Components.LoginComponent.TopBar

@Composable
fun LoginView(navigation: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    val scrollvertical = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(state = scrollvertical, enabled = true)
    ) {
        TopBar()
        Header()
        Spacer(modifier = Modifier.size(70.dp))
        BodyL(viewModel)
        BottomL(navigation)
        Spacer(modifier = Modifier.size(80.dp))
    }
}






