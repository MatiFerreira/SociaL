package es.proyect.besocial.presentation.components.login_component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import es.proyect.besocial.R
import es.proyect.besocial.domain.model.Response
import es.proyect.besocial.presentation.Login.LoginViewModel
import es.proyect.besocial.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyL(navController: NavController, viewModel: LoginViewModel) {
    //DECLARAMOS LOS VALORES Y OBSERVAMOS ESTOS PARA LA COMPROBACION DE DATOS
    //recoger datos metodo le damos unos iniciales
    var showPassword by rememberSaveable {
        mutableStateOf(true)
    }
    val loginflow = viewModel.loginFlow.collectAsState()

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = viewModel.email,
            onValueChange = {
                viewModel.email = it
            },
            label = { Text(text = "Username") },
            placeholder = { Text(text = "Username", fontSize = 20.sp, color = Color.DarkGray) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedLabelColor = Color.Black,
                focusedIndicatorColor = Color.Black
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.People,
                    contentDescription = ""
                )
            },
            maxLines = 1,
            singleLine = true
        )
        Spacer(modifier = Modifier.size(30.dp))
        TextField(
            value = viewModel.password,
            onValueChange = {
                viewModel.password = it
            },
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Password", fontSize = 20.sp, color = Color.DarkGray) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedLabelColor = Color.Black,
                focusedIndicatorColor = Color.Black
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        showPassword = !showPassword
                    }
                )
            },
            maxLines = 1,
            singleLine = true,
            visualTransformation =
            if (showPassword) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }

        )
        Spacer(modifier = Modifier.size(40.dp))
        Button(
            onClick = { viewModel.isLoginApp() },
            modifier = Modifier.size(width = 250.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            enabled = viewModel.CheckInfo(viewModel.email, viewModel.password)

        ) {
            Text(
                text = "LOGIN",
                fontFamily = FontFamily(Font(R.font.inderregular)),
                fontSize = 36.sp
            )
        }
    }

    loginflow.value.let {
        when (it) {
            Response.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { CircularProgressIndicator() }
            }

            is Response.Success -> {
                LaunchedEffect(Unit) {
                    navController.popBackStack(Screen.Login.route, true)
                    navController.navigate(route = Screen.Main.route)
                }
            }

            is Response.Failure -> {
                Toast.makeText(
                    LocalContext.current,
                    it.exception?.message ?: "ERROR DESCONOCIDO",
                    Toast.LENGTH_LONG
                ).show()

            }

            else -> {}
        }
    }
}