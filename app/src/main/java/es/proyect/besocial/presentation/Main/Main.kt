package es.proyect.besocial.presentation.Main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import es.proyect.besocial.R
import es.proyect.besocial.presentation.navigation.Screen

@Composable
fun MainView(navHostController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MyHeard()
        MyBody(viewModel, navHostController)
    }
}

@Composable
fun MyHeard() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = "BeSociaL",
                fontSize = 44.sp,
                fontFamily = FontFamily(Font(R.font.inderregular))
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBody(viewModel: MainViewModel, navHostController: NavHostController) {
    val name by rememberSaveable {
        mutableStateOf("Mathias Ferreira Ferreira")
    }
    var changeName by rememberSaveable {
        mutableStateOf("")
    }
    Column(Modifier.padding(20.dp)) {
        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Rounded.AccountCircle, contentDescription = "Your Profile icon",
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "$name",
                fontFamily = FontFamily(Font(R.font.inderregular)),
                fontSize = 26.sp
            )
        }
        Spacer(
            modifier = Modifier
                .height(40.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
        Row(
            Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ), modifier = Modifier.weight(0.05f),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = "EDIT PROFILE", fontFamily = FontFamily(Font(R.font.inderregular)))
            }
            Button(
                onClick = {
                    viewModel.logOut()
                    navHostController.popBackStack(Screen.Main.route, true)
                    navHostController.navigate(Screen.Login.route)

                },
                modifier = Modifier
                    .weight(0.05f)
                    .padding(start = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.Black
                ), border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = "LOG OUT", fontFamily = FontFamily(Font(R.font.inderregular)))
            }
        }
        Row(
            Modifier.padding(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "NAME: ", fontFamily = FontFamily(Font(R.font.inderregular)))
            TextField(
                value = "Nombre Usuario ",
                onValueChange = {},
                enabled = false,
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    textColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "SURNAME: ", fontFamily = FontFamily(Font(R.font.inderregular)))
            TextField(
                value = "Apellido",
                onValueChange = {},
                enabled = false,
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    textColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "EMAIL: ", fontFamily = FontFamily(Font(R.font.inderregular)))
            TextField(
                value = "Correo electronico",
                onValueChange = {},
                enabled = false,
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    textColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }

}
