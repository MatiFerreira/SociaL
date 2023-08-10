package es.proyect.besocial.presentation.components.RegisterComponent

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import es.proyect.besocial.R
import es.proyect.besocial.domain.model.Response
import es.proyect.besocial.presentation.navigation.Screen
import es.proyect.besocial.presentation.register.RegisterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyR(
    navigation: NavHostController,
    viewModel: RegisterViewModel
) {
    var showPassword by rememberSaveable {
        mutableStateOf(true)
    }
    val flowregister = viewModel.regiterFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = {
                viewModel.email = it
            },
            placeholder = { Text(text = "Enter Email id") }, leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
            }, maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.size(30.dp))

        TextField(
            value = viewModel.userName,
            onValueChange = {
                viewModel.userName = it
            },
            placeholder = { Text(text = "Username") },
            label = { Text(text = "Create Username") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
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
            placeholder = { Text(text = "Password") },
            label = { Text(text = "Create Password") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
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
            visualTransformation = if (showPassword) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
        )
        Spacer(modifier = Modifier.size(40.dp))
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier.size(width = 250.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            enabled = viewModel.CheckInfo(viewModel.email, viewModel.password, viewModel.userName)

        ) {
            Text(
                text = "Sign Up",
                fontFamily = FontFamily(Font(R.font.inderregular)),
                fontSize = 36.sp,
                modifier = Modifier
                    .clickable {
                        viewModel.signUp()
                    }
            )
        }
    }
    flowregister.value.let {
        when (it) {
            Response.Loading -> {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(21.dp), color = Color.White)
                }
            }

            is Response.Success -> {
                LaunchedEffect(Unit) {
                    viewModel.createUser()
                    navigation.popBackStack(Screen.Login.route, true)//para eliminar historial
                    navigation.navigate(Screen.Login.route)
                }
            }

            is Response.Failure -> {
                Toast.makeText(
                    LocalContext.current,
                    it.exception?.message ?: "ERROR DESCONOCIDO",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
        }
    }
}
