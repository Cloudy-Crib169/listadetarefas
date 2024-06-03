package com.marcos.listadetarefacompose.itemlista

import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.google.api.Context
import com.marcos.listadetarefacompose.R
import com.marcos.listadetarefacompose.model.Tarefa
import com.marcos.listadetarefacompose.repositorio.TarefasRepositorio
import com.marcos.listadetarefacompose.ui.theme.Black
import com.marcos.listadetarefacompose.ui.theme.Purple80
import com.marcos.listadetarefacompose.ui.theme.RADIO_BUTTON_GREEN_SELECTED
import com.marcos.listadetarefacompose.ui.theme.RADIO_BUTTON_RED_SELECTED
import com.marcos.listadetarefacompose.ui.theme.RADIO_BUTTON_YELLOW_SELECTED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TarefaItem(
    positon: Int,
    listaTarefas: MutableList<Tarefa>,
    context : android.content.Context,
    navController: NavController
){

    val tituloTarefa = listaTarefas[positon].tarefa
    val descricaoTarefa= listaTarefas[positon].descricao
    val prioriade= listaTarefas[positon].prioridade

    val scope = rememberCoroutineScope()
    val tarefasRepositorio = TarefasRepositorio()

    fun dialogDeletar(){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar tarefa")
            .setMessage("Deseja excluir a tarefa?")
            .setPositiveButton("Sim"){_,_->
                tarefasRepositorio.deletarTarefa(tituloTarefa.toString())

                scope.launch(Dispatchers.Main){
                    listaTarefas.removeAt(positon)
                    navController.navigate("listaTarefas")
                    Toast.makeText(context,"Sucesso em deletar a tarefa",Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Não"){_,_->

            }
            .show()
    }

    var nivelDePrioridade: String= when(prioriade){
       0 -> {
           "Sem Prioridade"
       }
        1 -> {
            "Prioridade Baixa"
        }
        2 -> {
            "Prioridade Media"
        }
        else -> {
            "Prioridade Alta"
        }
    }

    val color = when(prioriade){
        0 ->{
            Color.Black
        }
        1-> {
            RADIO_BUTTON_GREEN_SELECTED
        }
        2-> {
            RADIO_BUTTON_YELLOW_SELECTED
        }
        else ->{
            RADIO_BUTTON_RED_SELECTED
        }
    }




   Card(
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(40.dp,40.dp,40.dp,0.dp)
   ){
         
       ConstraintLayout(
           modifier = Modifier.padding(20.dp)
       ) {
           
           val (txtTitulo,txtDescricao,cardPrioridade,txtPrioridade,btDeletar) = createRefs()
           
           Text(
               text = tituloTarefa.toString(),
               modifier = Modifier.constrainAs(txtTitulo){
                   top.linkTo(parent.top, margin = 10.dp)
                   start.linkTo(parent.start, margin = 10.dp)
               }
           )

           Text(
               text = descricaoTarefa.toString(),
               modifier = Modifier.constrainAs(txtDescricao){
                   top.linkTo(txtTitulo.bottom, margin = 10.dp)
                   start.linkTo(parent.start, margin = 10.dp)
               }
           )

           Text(
               text = nivelDePrioridade,
               modifier = Modifier.constrainAs(txtPrioridade){
                   top.linkTo(txtDescricao.bottom, margin = 10.dp)
                   start.linkTo(parent.start, margin = 10.dp)
                   bottom.linkTo(parent.bottom, margin = 10.dp)
               }
           )

           Card(
               colors = CardDefaults.cardColors(
                         containerColor = color
               ),
               modifier = Modifier
                   .size(20.dp)
                   .constrainAs(cardPrioridade) {
                       top.linkTo(txtDescricao.bottom, margin = 10.dp)
                       start.linkTo(txtPrioridade.end, margin = 10.dp)
                       bottom.linkTo(parent.bottom, margin = 10.dp)
                   }
           ) {

           }

           IconButton(onClick = {
                dialogDeletar()
           },
               modifier = Modifier.constrainAs(btDeletar){
                   top.linkTo(txtDescricao.bottom, margin = 10.dp)
                   start.linkTo(cardPrioridade.end, margin = 30.dp)
                   end.linkTo(parent.end, margin = 10.dp)
                   bottom.linkTo(parent.bottom, margin = 10.dp)
               }
           ) {
               Image(imageVector = ImageVector.vectorResource(id= R.drawable.ic_delete), contentDescription = null)
           }
       }

   }

}






