package com.example.minhastarefas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val OPERACAO_CADASTRAR = 1
    private val OPERACAO_EDITAR = 2
    private val OPERACAO_DELETAR = 3

    private val TELA_CADASTRO = 1

    private lateinit var lvMainTarefas: ListView
    private lateinit var fabMainAdicionar: FloatingActionButton

    private lateinit var tarefaDAO: TarefaDAO
    private lateinit var listaTarefas: ArrayList<Tarefa>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tarefaDAO = TarefaDAO(this)
        this.listaTarefas = tarefaDAO.selectAll()

        this.lvMainTarefas = findViewById(R.id.lvMainTarefas)
        this.lvMainTarefas.adapter = AdapterTarefas(this.listaTarefas, this)
        this.lvMainTarefas.setOnItemClickListener(OnItemClick())
        this.lvMainTarefas.setOnItemLongClickListener(OnItemLongClick())


        this.fabMainAdicionar = findViewById(R.id.fabMainAdicionar)
        this.fabMainAdicionar.setOnClickListener(OnClickBotaoCadastro())

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        atualizaLista()


    }

    inner class OnClickBotaoCadastro : View.OnClickListener {
        override fun onClick(v: View?) {
            val it = Intent(this@MainActivity, CadastroActivity::class.java)
            it.putExtra("OPERACAO", OPERACAO_CADASTRAR)
            startActivityForResult(it, TELA_CADASTRO)

        }
    }

    inner class OnItemClick : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val tarefa = this@MainActivity.listaTarefas.get(position)
            val it = Intent(this@MainActivity, CadastroActivity::class.java)
            it.putExtra("OPERACAO", OPERACAO_EDITAR)
            it.putExtra("ID_TAREFA", tarefa.id)
            startActivityForResult(it, TELA_CADASTRO)
        }

    }

    inner class OnItemLongClick : AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {

            val tarefa = this@MainActivity.listaTarefas.get(position)
            val it = Intent(this@MainActivity, CadastroActivity::class.java)
            it.putExtra("OPERACAO", OPERACAO_DELETAR)
            it.putExtra("ID_TAREFA", tarefa.id)
            startActivityForResult(it, TELA_CADASTRO)

            return true
        }
    }

    private fun atualizaLista(){
        this.listaTarefas = tarefaDAO.selectAll()
        this.lvMainTarefas.adapter = AdapterTarefas(this.listaTarefas, this)
        (this.lvMainTarefas.adapter as AdapterTarefas).notifyDataSetChanged()
    }

}