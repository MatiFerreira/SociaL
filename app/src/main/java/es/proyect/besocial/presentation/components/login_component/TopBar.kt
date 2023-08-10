package es.proyect.besocial.presentation.components.login_component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import es.proyect.besocial.R
import es.proyect.besocial.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navigation: NavHostController) {
    TopAppBar(title = { Text(text = "SociaL", fontFamily = FontFamily(Font(R.font.inderregular))) },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.arrow), contentDescription = "",
                modifier = Modifier
                    .size(34.dp)
                    .clickable {
                        navigation.navigate(Screen.Login.route) {
                            popUpTo(Screen.Register.route) {
                                inclusive = true
                            }
                        }
                    }
            )

        }
    )
}