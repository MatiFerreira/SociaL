package es.proyect.besocial.presentation.components.login_component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import es.proyect.besocial.presentation.navigation.Screen

@Composable
fun BottomL(navigation: NavHostController) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Olvidaste Tu Contraseña?",
            fontSize = 15.sp, color = Color.Gray,
            modifier = Modifier
                .clickable {
                    navigation.navigate(Screen.Lost.route)
                }
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(text = "O Crear una Nueva Cuenta", fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .clickable { navigation.navigate(Screen.Register.route) }
        )
    }
}
