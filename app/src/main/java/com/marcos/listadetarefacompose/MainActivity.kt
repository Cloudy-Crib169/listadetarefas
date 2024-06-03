package com.marcos.listadetarefacompose

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marcos.listadetarefacompose.ui.theme.ListaDeTarefaComposeTheme
import com.marcos.listadetarefacompose.view.ListaTarefas
import com.marcos.listadetarefacompose.view.SalvarTarefas

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaDeTarefaComposeTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "ListaTarefas"){
                    composable(
                        route = "ListaTarefas"
                    ){
                        ListaTarefas(navController)
                    }

                    composable(
                        route = "salvarTarefas"
                    ){
                        SalvarTarefas(navController)
                    }
                }
            }
        }
    }
}



