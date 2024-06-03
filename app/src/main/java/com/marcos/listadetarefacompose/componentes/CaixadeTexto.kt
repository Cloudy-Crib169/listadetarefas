package com.marcos.listadetarefacompose.componentes

import android.content.pm.LabeledIntent
import android.icu.text.AlphabeticIndex.Bucket.LabelType
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.marcos.listadetarefacompose.ui.theme.Black
import com.marcos.listadetarefacompose.ui.theme.LIGHT_BLUE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixadeTexto(
    
    value: String,
    onValueChange: (String)-> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int
){
    
    
    OutlinedTextField(
        value = value , 
        onValueChange,
        modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLines,
        colors = OutlinedTextFieldDefaults.run {
            colors(
                focusedBorderColor = LIGHT_BLUE,
                focusedLabelColor = LIGHT_BLUE,
                cursorColor = LIGHT_BLUE


            )
        }


    )
}

