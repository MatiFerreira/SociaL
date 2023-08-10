package es.proyect.besocial.presentation.LostPsswrd

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import es.proyect.besocial.R


@Composable
fun MyLostPaswrdView(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(20.dp)
    ) {
        MyHeard()
        MyBody()
        MyBottom()
    }
}

@Composable
fun MyBottom() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
            )
        ) {
            Text(text = "RECOVERY", color = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBody() {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var newpassword by rememberSaveable {
        mutableStateOf("")
    }
    var newpassword2 by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = email,
            onValueChange = { email = it },
            placeholder = { Text(text = "EMAIL", color = Color.Black) })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = newpassword,
            onValueChange = { newpassword = it },
            placeholder = { Text(text = " NEW PASSWORD", color = Color.Black) },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = newpassword2,
            onValueChange = { newpassword2 = it },
            placeholder = { Text(text = " NEW PASSWORD", color = Color.Black) },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun MyHeard() {
    Column(
        Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SociaL", fontFamily = FontFamily(Font(R.font.inderregular)), fontSize = 41.sp
        )
        Icon(
            imageVector = Icons.Rounded.Person, contentDescription = "", Modifier.size(31.dp)
        )
        Text(
            text = "RECOVERY YOUR PASSWORD",
            fontFamily = FontFamily(Font(R.font.inderregular)),
            textDecoration = TextDecoration.Underline,
            fontSize = 19.sp
        )

    }
}
