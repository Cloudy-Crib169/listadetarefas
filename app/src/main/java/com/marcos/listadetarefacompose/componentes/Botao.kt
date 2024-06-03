package com.marcos.listadetarefacompose.componentes

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcos.listadetarefacompose.ui.theme.Black

@Composable
fun Botao(
    onClick:()-> Unit,
    modifier: Modifier,
    texto: String
){


    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = Black
        )

    ) {
        Text(text = texto, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}