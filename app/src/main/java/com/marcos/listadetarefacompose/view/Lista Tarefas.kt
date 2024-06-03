package com.marcos.listadetarefacompose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.marcos.listadetarefacompose.R
import com.marcos.listadetarefacompose.itemlista.TarefaItem
import com.marcos.listadetarefacompose.model.Tarefa
import com.marcos.listadetarefacompose.repositorio.TarefasRepositorio
import com.marcos.listadetarefacompose.ui.theme.Black
import com.marcos.listadetarefacompose.ui.theme.Purple700
import com.marcos.listadetarefacompose.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListaTarefas(
    navController: NavController
){

    val tarefasRepositorio = TarefasRepositorio()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                   colors = TopAppBarDefaults.topAppBarColors(
                       containerColor = Color.Blue

                   ),
                title ={
                    Text(
                        text = "Lista de Tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,

                        )
                }

            )

        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                  navController.navigate(route = "salvarTarefas")
                },
                Modifier.background(color= Black
                )
            ) {
               Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                   contentDescription ="Icone de salvar tarefa!"
               )
            }
        }

    ) {

          val listaTarefas = tarefasRepositorio.recuperarTarefas().collectAsState(mutableListOf()).value

        LazyColumn {
               itemsIndexed(listaTarefas){positon, _, ->
                   TarefaItem(positon = positon, listaTarefas = listaTarefas, context = context, navController = navController)

               }
        }
    }
}