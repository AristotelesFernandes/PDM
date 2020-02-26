package com.example.autentica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var etUsuario: EditText
    private lateinit var etSenha: EditText
    private lateinit var btCancelar: Button
    private lateinit var btOK: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etUsuario = findViewById(R.id.etUsuario)
        this.etSenha = findViewById(R.id.etSenha)
        this.btCancelar = findViewById(R.id.btCancelar)
        this.btOK = findViewById(R.id.btOK)

        this.btCancelar.setOnClickListener({
            Log.i("APP_AUTENTICA", "i - Cancelou")
            Log.e("APP_AUTENTICA", "e - Cancelou")
            Log.w("APP_AUTENTICA", "w - Cancelou")
            this.etUsuario.text.clear()
            this.etSenha.text.clear()
            Toast.makeText(this, "Voce cancelou!", Toast.LENGTH_SHORT).show()
        })

        this.btOK.setOnClickListener(OnClickBotaoAutenticar())

    }

    fun proximaTela() {
        val intent = Intent(this, NovaTelaActivity::class.java)
        startActivity(intent)
    }

    inner class OnClickBotaoAutenticar : View.OnClickListener {
        override fun onClick(v: View?) {
            var usuario = this@MainActivity.etUsuario.text.toString()
            var senha = this@MainActivity.etSenha.text.toString()

            Log.i("APP_AUTENTICA", "$usuario - $senha")

            if (usuario == "admin" && senha == "1234"){
                Toast.makeText(this@MainActivity, "Sucesso!", Toast.LENGTH_SHORT).show()
                Log.i("APP_AUTENTICA", "Sucesso!")
                proximaTela()
                etUsuario.text.clear()
                etSenha.text.clear()
            } else {
                Toast.makeText(this@MainActivity, "Senha invalida!", Toast.LENGTH_SHORT).show()
                Log.e("APP_AUTENTICA", "Senha invalida!")
                etSenha.text.clear()
            }
        }
    }

}
