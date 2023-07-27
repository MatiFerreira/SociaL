package es.proyect.besocial.presentation.Login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import es.proyect.besocial.R
import es.proyect.besocial.presentation.navigation.Screen

@Composable
fun LoginView(navigation: NavHostController, viewModel: LoginViewModel) {
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
                .clickable { /*TODO*/ }
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(text = "O Crear una Nueva Cuenta", fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .clickable { navigation.navigate(Screen.Register.route) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyL(viewModel: LoginViewModel) {
    //DECLARAMOS LOS VALORES Y OBSERVAMOS ESTOS PARA LA COMPROBACION DE DATOS
    //recoger datos metodo le damos unos iniciales
    val emailState: String by viewModel.email.observeAsState(initial = "")
    val passwrdState: String by viewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by viewModel.loadingEnable.observeAsState(initial = false)
    var showPassword by rememberSaveable {
        mutableStateOf(true)
    }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = emailState,
            onValueChange = { viewModel.onLoginUpdate(email = it, password = passwrdState) },
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
            value = passwrdState,
            onValueChange = {
                viewModel.onLoginUpdate(
                    email = emailState,
                    password = it
                )
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
            )

        ) {
            Text(
                text = "LOGIN",
                fontFamily = FontFamily(Font(R.font.inderregular)),
                fontSize = 36.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(title = { Text(text = "SociaL", fontFamily = FontFamily(Font(R.font.inderregular))) },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.arrow), contentDescription = "",
                modifier = Modifier
                    .size(34.dp)
                    .clickable { }
            )

        }
    )
}

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
