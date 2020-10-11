package com.example.minhastarefas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AdapterTarefas (private var tarefas: ArrayList<Tarefa>, var context: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val cor = this.tarefas.get(position)
        val linha: View
        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            linha = inflater.inflate(R.layout.lista_tarefa, null)
        } else {
            linha = convertView
        }

        val ivTarefaStatusPreview = linha.findViewById<ImageView>(R.id.ivTarefaStatusPreview)
        val tvTarefaDescricao = linha.findViewById<TextView>(R.id.tvTarefaDescricao)
        val tvTarefaPrioridade = linha.findViewById<TextView>(R.id.tvTarefaPrioridade)

        val tarefa = tarefas.get(position)

        tvTarefaDescricao.text = tarefa.descricao
        tvTarefaPrioridade.text = "Prioridade: ${tarefa.prioridade.toString()}"

        when(tarefa.status) {
            0 ->
                ivTarefaStatusPreview.setImageResource(R.mipmap.ic_aberto)
            1 ->
                ivTarefaStatusPreview.setImageResource(R.mipmap.ic_executando)
            2 ->
                ivTarefaStatusPreview.setImageResource(R.mipmap.ic_concluido)
        }

        return linha
    }

    override fun getItem(position: Int): Any {
        return this.tarefas.get(position)
    }

    override fun getItemId(position: Int): Long {
        return -1
    }

    override fun getCount(): Int {
        return this.tarefas.count()
    }
}