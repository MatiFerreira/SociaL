package es.proyect.besocial.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import es.proyect.besocial.R
import es.proyect.besocial.presentation.Login.TopBar
import es.proyect.besocial.presentation.navigation.Screen

@Composable
fun RegisterView(navigation: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TopBar()
        HeaderR()
        BodyR(viewModel = RegisterViewModel(), navigation)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyR(viewModel: RegisterViewModel, navigation: NavHostController) {
    var showPassword by rememberSaveable {
        mutableStateOf(true)
    }
    val emailstate: String by viewModel.email.observeAsState(initial = "")
    val passwordstate: String by viewModel.password.observeAsState(initial = "")
    val nickNamestate: String by viewModel.userName.observeAsState(initial = "")
    val isRegisterstate: Boolean by viewModel.registerEnable.observeAsState(initial = false)
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = emailstate,
            onValueChange = {
                viewModel.updateDataRegister(
                    email = it,
                    passwordstate,
                    nickNamestate
                )
            },
            placeholder = { Text(text = "Enter Email id") }, leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
            }, maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.size(30.dp))

        TextField(
            value = nickNamestate,
            onValueChange = {
                viewModel.updateDataRegister(
                    email = emailstate,
                    passwordstate,
                    nickname = it
                )
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
            value = passwordstate,
            onValueChange = {
                viewModel.updateDataRegister(
                    email = emailstate,
                    password = it,
                    nickNamestate
                )
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
            )

        ) {
            Text(
                text = "Sign Up",
                fontFamily = FontFamily(Font(R.font.inderregular)),
                fontSize = 36.sp,
                modifier = Modifier
                    .clickable {
                        viewModel.isRegister(navigation)
                    }
            )
        }
    }
}

@Composable
fun HeaderR() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Text(
            text = "Create ", fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Account :)",
            fontSize = 28.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}
