package es.proyect.besocial.presentation.components.main_component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import es.proyect.besocial.presentation.Main.MainViewModel
import es.proyect.besocial.presentation.navigation.Screen


@Composable
fun BodyMain(navigation: NavHostController, viewModel: MainViewModel) {
    Column() {
        Text(text = "Sign Out", modifier = Modifier.clickable {
            viewModel.logOut()
            navigation.navigate(Screen.Login.route)
        })
    }
}