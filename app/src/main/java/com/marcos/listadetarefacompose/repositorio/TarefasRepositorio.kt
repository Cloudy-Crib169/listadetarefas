package com.marcos.listadetarefacompose.repositorio

import com.marcos.listadetarefacompose.datasource.DataSource
import com.marcos.listadetarefacompose.model.Tarefa
import kotlinx.coroutines.flow.Flow

class TarefasRepositorio() {

    private val dataSource = DataSource()

    fun salvarTarefa(tarefa:String, descricao: String, prioridade:Int){
        dataSource.salvaTarefa(tarefa,descricao,prioridade)
    }

    fun recuperarTarefas():Flow<MutableList<Tarefa>>{
        return dataSource.recuperarTarefas()
    }

    fun deletarTarefa(tarefa:String){
        dataSource.deletarTarefa(tarefa)
    }
}