package com.example.minhastarefas

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class CadastroActivity : AppCompatActivity() {
    private val OPERACAO_CADASTRAR = 1
    private val OPERACAO_EDITAR = 2
    private val OPERACAO_DELETAR = 3

    private lateinit var tarefaDAO: TarefaDAO

    private lateinit var etCadastroDescricao: EditText
    private lateinit var sbCadastroPrioridade: SeekBar
    private lateinit var tvCadastroPrioridade: TextView
    private lateinit var rgCadastroStatus: RadioGroup
    private lateinit var rbCadastroStatus0: RadioButton
    private lateinit var rbCadastroStatus1: RadioButton
    private lateinit var rbCadastroStatus2: RadioButton
    private lateinit var btCadastro: Button
    private lateinit var btCadastroCancelar:Button

    //Default CADASTRAR
    private var operacao: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        this.etCadastroDescricao = findViewById(R.id.etCadastroDescricao)
        this.sbCadastroPrioridade = findViewById(R.id.sbCadastroPrioridade)
        this.tvCadastroPrioridade = findViewById(R.id.tvCadastroPrioridade)
        this.rgCadastroStatus = findViewById(R.id.rgCadastroStatus)
        this.rbCadastroStatus0 = findViewById(R.id.rbCadastroStatus0)
        this.rbCadastroStatus1 = findViewById(R.id.rbCadastroStatus1)
        this.rbCadastroStatus2 = findViewById(R.id.rbCadastroStatus2)
        this.btCadastro = findViewById(R.id.btCadastro)
        this.btCadastroCancelar = findViewById(R.id.btCadastroCancelar)

        this.sbCadastroPrioridade.setOnSeekBarChangeListener(ExibePrioridade())
        this.btCadastro.setOnClickListener(OnClickBotaoCadastro())
        this.btCadastroCancelar.setOnClickListener(OnClickBotaoCadastroCancelar())


        this.tarefaDAO = TarefaDAO(this)

    }

    inner class OnClickBotaoCadastro : View.OnClickListener {
        override fun onClick(v: View?) {

            if (this@CadastroActivity.etCadastroDescricao.text == null){
                Toast.makeText(this@CadastroActivity, "Informe uma descrção!", Toast.LENGTH_LONG).show()
            } else if (this@CadastroActivity.sbCadastroPrioridade.progress == -1) {
                Toast.makeText(this@CadastroActivity, "Informe uma prioridade!", Toast.LENGTH_LONG).show()
            } else if (this@CadastroActivity.rgCadastroStatus.checkedRadioButtonId == -1) {
                Toast.makeText(this@CadastroActivity, "Informe um status!", Toast.LENGTH_LONG).show()
            } else {

                var desc = this@CadastroActivity.etCadastroDescricao.text.toString()
                var prior: Int = this@CadastroActivity.sbCadastroPrioridade.progress
                var stat = 1

                for (i in 0 until this@CadastroActivity.rgCadastroStatus.getChildCount()) {
                    if((this@CadastroActivity.rgCadastroStatus.getChildAt(i) as RadioButton).isChecked){
                        stat = i
                    }
                }

                if (this@CadastroActivity.operacao == OPERACAO_CADASTRAR ) {
                    var tarefa = Tarefa(desc, prior, stat)
                    this@CadastroActivity.tarefaDAO.add(tarefa)
                    Toast.makeText(this@CadastroActivity, "Tarefa cadastrada com sucesso.", Toast.LENGTH_LONG).show()
                } else if (this@CadastroActivity.operacao == OPERACAO_EDITAR ) {
                    var id = intent.getIntExtra("ID_TAREFA", -1)
                    var tarefa = Tarefa(id, desc, prior, stat)
                    this@CadastroActivity.tarefaDAO.update(tarefa)
                    Toast.makeText(this@CadastroActivity, "Tarefa atualizada com sucesso.", Toast.LENGTH_LONG).show()
                } else if (this@CadastroActivity.operacao == OPERACAO_DELETAR ) {
                    var id = intent.getIntExtra("ID_TAREFA", -1)
                    var tarefa = Tarefa(id, desc, prior, stat)
                    this@CadastroActivity.tarefaDAO.delete(tarefa)
                    Toast.makeText(this@CadastroActivity, "Tarefa removida com sucesso.", Toast.LENGTH_LONG).show()
                }

                finish()
            }
        }
    }

    inner class OnClickBotaoCadastroCancelar : View.OnClickListener {
        override fun onClick(v: View?) {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        this.operacao = intent.getIntExtra("OPERACAO", 1)

        if (operacao == OPERACAO_CADASTRAR) {
            this.rbCadastroStatus0.isChecked = true

            for (i in 0 until this.rgCadastroStatus.getChildCount()) {
                (this.rgCadastroStatus.getChildAt(i) as RadioButton).isEnabled = false
            }

            this.btCadastro.text = "Cadastrar"

        } else if (operacao == OPERACAO_EDITAR){
            var id = intent.getIntExtra("ID_TAREFA", -1)

            var tarefa = this.tarefaDAO.selectOne(id)

            this.etCadastroDescricao.setText(tarefa.descricao)
            this.sbCadastroPrioridade.setProgress(tarefa.prioridade)

            for (i in 0 until this.rgCadastroStatus.getChildCount()) {
                (this.rgCadastroStatus.getChildAt(i) as RadioButton).isEnabled = true
            }

            when(tarefa.status){
                0 ->
                    this.rbCadastroStatus0.isChecked = true
                1 ->
                    this.rbCadastroStatus1.isChecked = true
                2->
                    this.rbCadastroStatus2.isChecked = true
            }

            this.btCadastro.text = "Editar"

        } else if (operacao == OPERACAO_DELETAR){
            var id = intent.getIntExtra("ID_TAREFA", -1)

            var tarefa = this.tarefaDAO.selectOne(id)

            this.etCadastroDescricao.setText(tarefa.descricao)
            this.etCadastroDescricao.isEnabled = false
            this.sbCadastroPrioridade.setProgress(tarefa.prioridade)
            this.sbCadastroPrioridade.isEnabled = false

            when(tarefa.status){
                0 ->
                    this.rbCadastroStatus0.isChecked = true
                1 ->
                    this.rbCadastroStatus1.isChecked = true
                2->
                    this.rbCadastroStatus2.isChecked = true
            }

            for (i in 0 until this.rgCadastroStatus.getChildCount()) {
                (this.rgCadastroStatus.getChildAt(i) as RadioButton).isEnabled = false
            }
            this.btCadastro.text = "Remover"
        }


    }

    inner class ExibePrioridade: SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            this@CadastroActivity.tvCadastroPrioridade.text = progress.toString()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {

        }

    }

}