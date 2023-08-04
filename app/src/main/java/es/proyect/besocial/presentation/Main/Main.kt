package es.proyect.besocial.presentation.Main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import es.proyect.besocial.presentation.navigation.Screen

@Composable
fun MainView(navHostController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Button(onClick = {
            viewModel.logOut()
            navHostController.navigate(Screen.Login.route) {
                popUpTo(Screen.Main.route) { inclusive = true }
            }
        }) {
            Text(text = "CERRAR SESSION! de ${viewModel.userdata.nickName}  ADIOS")
        }
    }
}
