package com.example.minhascorespreferidas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import layout.CorPreferida

class CadastroActivity : AppCompatActivity() {

    val FORM_MAIN = 1

    private lateinit var etCadastroNome: EditText
    private lateinit var etCadastroCodigo: EditText
    private lateinit var btCadastro: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        this.etCadastroNome = findViewById(R.id.etCadastroNome)
        this.etCadastroCodigo = findViewById(R.id.etCadastroCodigo)
        this.btCadastro = findViewById(R.id.btCadastro)

        this.btCadastro.setOnClickListener(OnClickBotaoCadastro())
    }

    inner class OnClickBotaoCadastro : View.OnClickListener {
        override fun onClick(v: View?) {

            if (this@CadastroActivity.etCadastroNome.toString() == "") {
                Toast.makeText(this@CadastroActivity, "Nome da Cor obrigatório!", Toast.LENGTH_LONG).show()
            } else if (this@CadastroActivity.etCadastroCodigo.toString() == "") {
                Toast.makeText(this@CadastroActivity,"Código da cor obrigatório!", Toast.LENGTH_LONG).show()
            } else {

                var nome = this@CadastroActivity.etCadastroNome.text.toString()
                var codigo = this@CadastroActivity.etCadastroCodigo.text.toString()
                var cor = CorPreferida(nome, "#"+codigo)

                val intent = Intent()
                intent.putExtra("COR", cor)
                setResult(Activity.RESULT_OK, intent)

                finish()
            }
        }
    }

}