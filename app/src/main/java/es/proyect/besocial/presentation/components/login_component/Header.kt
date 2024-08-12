package es.proyect.besocial.presentation.components.login_component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Text(
            text = "Bienvenido de Nuevo!",
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = "Entra Con Tu Usuario & Contraseña", fontSize = 16.sp, color = Color.DarkGray)
    }
}
