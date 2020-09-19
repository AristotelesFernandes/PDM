package com.example.arrocheonumero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val APP_ARROCHA = "APP_ARROCHA"

    private lateinit var tvIntervalo: TextView
    private lateinit var etValor: EditText
    private lateinit var btArrocha: Button

    private var numero = 0
    private var valorMinimo = 1
    private var valorMaximo = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvIntervalo = findViewById(R.id.tvIntervalo)
        this.etValor = findViewById(R.id.etValor)
        this.btArrocha = findViewById(R.id.btArrochar)
        this.btArrocha.setOnClickListener(OnClickBotaoArrochar())
    }

    private fun gerarValor(): Int {
        var n = Random.nextInt(2, 99)
        Log.i(this.APP_ARROCHA, "Número gerado: ${n}")
        return n
    }

    inner class OnClickBotaoArrochar : View.OnClickListener {
        override fun onClick(v: View?) {
            if (this@MainActivity.etValor.text.toString() == "") {
                Toast.makeText(this@MainActivity,"Informe um valor dentro do intervalo!", Toast.LENGTH_LONG).show()
            } else {

                var valorInformado = this@MainActivity.etValor.text.toString().toInt()

                if (valorInformado == this@MainActivity.numero) {
                    val it = Intent(this@MainActivity, ResultadoActivity::class.java)
                    it.putExtra("RESULTADO", "Você perdeu!")
                    it.putExtra(
                        "MENSAGEM",
                        "Você informou o valor gerado (${this@MainActivity.numero})!"
                    )
                    startActivity(it)

                } else if (valorInformado < this@MainActivity.valorMinimo || valorInformado > this@MainActivity.valorMaximo) {
                    Toast.makeText(this@MainActivity,"Informe um valor dentro do intervalo!", Toast.LENGTH_LONG).show()
                    this@MainActivity.etValor.text.clear()

                } else if (valorInformado == this@MainActivity.valorMinimo || valorInformado == this@MainActivity.valorMaximo) {
                    val it = Intent(this@MainActivity, ResultadoActivity::class.java)
                    it.putExtra("RESULTADO", "Você perdeu!")
                    it.putExtra("MENSAGEM","Você informou o valor do limite (${valorInformado})!"
                    )
                    startActivity(it)
                } else {
                    if (valorInformado < this@MainActivity.numero) {
                        this@MainActivity.valorMinimo = valorInformado
                    } else if (valorInformado > this@MainActivity.numero) {
                        this@MainActivity.valorMaximo = valorInformado
                    }

                    this@MainActivity.tvIntervalo.text = "${this@MainActivity.valorMinimo} - ${this@MainActivity.valorMaximo}"
                    this@MainActivity.etValor.text.clear()
                }

                if ((this@MainActivity.valorMinimo + 1) == (this@MainActivity.valorMaximo - 1)) {
                    val it = Intent(this@MainActivity, ResultadoActivity::class.java)
                    it.putExtra("RESULTADO", "Você ganhou!")
                    it.putExtra("MENSAGEM","Você arrochou o valor gerado (${this@MainActivity.numero})!"
                    )
                    startActivity(it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        this@MainActivity.valorMinimo = 1
        this@MainActivity.valorMaximo = 100
        this@MainActivity.tvIntervalo.text = "${this@MainActivity.valorMinimo} - ${this@MainActivity.valorMaximo}"
        this@MainActivity.etValor.text.clear()
        this@MainActivity.numero = gerarValor()
    }

}