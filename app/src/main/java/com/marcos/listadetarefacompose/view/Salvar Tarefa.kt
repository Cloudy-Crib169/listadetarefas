package com.marcos.listadetarefacompose.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.marcos.listadetarefacompose.componentes.Botao
import com.marcos.listadetarefacompose.componentes.CaixadeTexto
import com.marcos.listadetarefacompose.constantes.Constantes
import com.marcos.listadetarefacompose.repositorio.TarefasRepositorio
import com.marcos.listadetarefacompose.ui.theme.Black
import com.marcos.listadetarefacompose.ui.theme.Purple700
import com.marcos.listadetarefacompose.ui.theme.RADIO_BUTTON_GREEN_DISABLED
import com.marcos.listadetarefacompose.ui.theme.RADIO_BUTTON_GREEN_SELECTED
import com.marcos.listadetarefacompose.ui.theme.RADIO_BUTTON_RED_DISABLED
import com.marcos.listadetarefacompose.ui.theme.RADIO_BUTTON_RED_SELECTED
import com.marcos.listadetarefacompose.ui.theme.RADIO_BUTTON_YELLOW_DISABLED
import com.marcos.listadetarefacompose.ui.theme.RADIO_BUTTON_YELLOW_SELECTED
import com.marcos.listadetarefacompose.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SalvarTarefas(
    navController: NavController
){

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tarefasRepositorio = TarefasRepositorio()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue
                ),
                title ={ 
                    Text(
                        text = "SalvarTarefa",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                        )
                })
        }
    ) {
       var tituloTarefa by remember {
           mutableStateOf("")
       }

        var descricaoTarefa by remember {
            mutableStateOf("")
        }

        var semPrioridadeTarefa by remember {
            mutableStateOf(false)
        }
        var prioridadeBaixaTarefa by remember {
            mutableStateOf(false)
        }
        var prioridadeMediaTarefa by remember {
            mutableStateOf(false)
        }
        var prioridadeAltaTarefa by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {


            CaixadeTexto(
                value = tituloTarefa ,
                onValueChange = {
                  tituloTarefa = it
                } ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(70.dp, 80.dp, 60.dp, 0.dp),
                label = "Titulo Tarefa" ,
                maxLines = 1
            )

            CaixadeTexto(
                value = descricaoTarefa ,
                onValueChange = {
                    descricaoTarefa = it
                } ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(70.dp, 30.dp, 60.dp, 0.dp),
                label = "Descrição Tarefa" ,
                maxLines = 5
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                
                Text(text = "Nivel de prioridade")

                RadioButton(
                    selected = prioridadeBaixaTarefa ,
                    onClick = {
                        prioridadeBaixaTarefa = !prioridadeBaixaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_GREEN_DISABLED,
                        selectedColor = RADIO_BUTTON_GREEN_SELECTED
                    )
                )

                RadioButton(
                    selected = prioridadeMediaTarefa ,
                    onClick = {
                        prioridadeMediaTarefa = !prioridadeMediaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_YELLOW_DISABLED,
                        selectedColor = RADIO_BUTTON_YELLOW_SELECTED
                    )
                )

                RadioButton(
                    selected = prioridadeAltaTarefa ,
                    onClick = {
                        prioridadeAltaTarefa = !prioridadeAltaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_RED_DISABLED,
                        selectedColor = RADIO_BUTTON_RED_SELECTED
                    )
                )

            }

            Botao(
                onClick = {

                    var mensagem = true





                   scope.launch(Dispatchers.IO){
                       if (tituloTarefa.isEmpty()){
                           mensagem = false
                       }else if (tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeBaixaTarefa){
                           tarefasRepositorio.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_BAIXA)
                           mensagem = true

                       }else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeMediaTarefa){
                           tarefasRepositorio.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_MEDIA)
                           mensagem = true
                       }else if (tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeAltaTarefa){
                           tarefasRepositorio.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_ALTA)
                           mensagem = true
                       }else if (tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && semPrioridadeTarefa){
                           tarefasRepositorio.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.SEM_PRIORIDADE)
                           mensagem = true
                       }else if (tituloTarefa.isNotEmpty() && prioridadeBaixaTarefa){
                           tarefasRepositorio.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_BAIXA)
                           mensagem = true
                       }else if (tituloTarefa.isNotEmpty() && prioridadeMediaTarefa){
                           tarefasRepositorio.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_MEDIA)
                           mensagem = true
                       }else if (tituloTarefa.isNotEmpty() && prioridadeAltaTarefa){
                           tarefasRepositorio.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.PRIORIDADE_ALTA)
                           mensagem = true
                       }else{
                           tarefasRepositorio.salvarTarefa(tituloTarefa, descricaoTarefa, Constantes.SEM_PRIORIDADE)
                           mensagem = true
                       }
                   }

                    scope.launch(Dispatchers.Main){
                        if(mensagem){
                           Toast.makeText(context,"Sucesso ao salvar a tarefa!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }else {
                            Toast.makeText(context,"Titulo obrigatorio!", Toast.LENGTH_SHORT).show()
                        }


                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(20.dp) ,
                texto = "Salvar"
            )

        }


    }

}