package es.proyect.besocial.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import es.proyect.besocial.presentation.navigation.NavegationScreenController
import es.proyect.besocial.presentation.ui.theme.BeSociaLTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeSociaLTheme {
                val navigationController = rememberNavController()
                // A surface container using the 'background' color from the theme
                val navegation: NavegationScreenController = hiltViewModel()
                navegation.AppNavegation(navigation = navigationController)
            }
        }
    }
}