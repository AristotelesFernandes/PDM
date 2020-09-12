package com.example.acerteonumero

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private val logTag = "APP_ACERTE"

    private var valorGerado = 0
    private lateinit var llInicio: LinearLayout
    private lateinit var btInicio: Button
    private lateinit var llPalpite: LinearLayout
    private lateinit var tvDica1: TextView
    private lateinit var tvDica2: TextView
    private lateinit var tvDica3: TextView
    private lateinit var etPalpite: EditText
    private lateinit var btPalpite: Button
    private lateinit var tvResultado: TextView
    private lateinit var tvValorGerado: TextView

    private lateinit var barraDeAcao: android.app.ActionBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.llInicio = findViewById(R.id.llInicio)
        this.btInicio = findViewById(R.id.btInicio)
        this.llPalpite = findViewById(R.id.llPalpite)
        this.tvDica1 = findViewById(R.id.tvDica1)
        this.tvDica2 = findViewById(R.id.tvDica2)
        this.tvDica3 = findViewById(R.id.tvDica3)
        this.etPalpite = findViewById(R.id.etPalpite)
        this.btPalpite = findViewById(R.id.btPalpite)
        this.tvResultado = findViewById(R.id.tvResultado)
        this.tvValorGerado = findViewById(R.id.tvValorGerado)

        this.btInicio.setOnClickListener(OnClickBotaoIniciar())
        this.btPalpite.setOnClickListener(OnClickBotaoPalpite())

        this.barraDeAcao = actionBar!!
        this.barraDeAcao.setBackgroundDrawable(ColorDrawable(1))

    }

    fun gerarValorAleatorio(): Int {
        return Random.nextInt(1, 100)
    }

    fun listarDivisores(n: Int): List<Int> {
        var lista = mutableListOf<Int>()
        var i = 1
        for (i in 1..10) {
            if (n % i == 0)
                lista.add(i)
        }

        return lista
    }

    fun contarDivisores(n: Int): Int {
        var qtde = 0
        var i = 1
        while (i <= n) {
            if (n % i == 0)
                qtde++
            i++
        }
        return qtde
    }

    fun isNumeroPar(n: Int): Boolean {
        return (n % 2 == 0)
    }

    inner class OnClickBotaoIniciar : View.OnClickListener {
        override fun onClick(v: View?) {
            var listaDivisores = listOf<Int>()
            var qtdeDivisores = 0

            this@MainActivity.valorGerado = gerarValorAleatorio()

            Log.i(this@MainActivity.logTag, "O número gerado foi " + valorGerado.toString())

            qtdeDivisores = contarDivisores(valorGerado)
            listaDivisores = listarDivisores(valorGerado)


            this@MainActivity.llInicio.visibility = View.INVISIBLE

            this@MainActivity.tvDica1.text = "Os divisores do número entre 1 e 10 são " + listaDivisores.joinToString(", ") + "!"
            if (isNumeroPar(valorGerado))
                this@MainActivity.tvDica2.text = " O número é par!"
            else
                this@MainActivity.tvDica2.text = " O número é impar!"
            this@MainActivity.tvDica3.text = "O número possui " + qtdeDivisores.toString() + " divisores!"
            this@MainActivity.etPalpite.text.clear()
            this@MainActivity.llPalpite.visibility = View.VISIBLE

        }
    }

    inner class OnClickBotaoPalpite : View.OnClickListener {
        override fun onClick(v: View?) {
            var palpite = 0
            var resultado = ""
            if (this@MainActivity.etPalpite.text.toString() == "") {
                Log.e(this@MainActivity.logTag, "Nenhum palpite foi informado.")
                Toast.makeText(this@MainActivity, "Informe um palpite!", Toast.LENGTH_SHORT).show()
            } else {
                palpite = this@MainActivity.etPalpite.text.toString().toInt()

                if (palpite == valorGerado) {
                    resultado = "Parabéns! Você acertou o número!"
                    Log.i(this@MainActivity.logTag, "Usuário acertou o número.")
                } else {
                    resultado = "Que pena! Você errou o número!"
                    Log.e(this@MainActivity.logTag, "Usuário errou o número.")
                }

                this@MainActivity.tvResultado.text = resultado
                this@MainActivity.tvValorGerado.text = "O número gerado foi " + valorGerado.toString() + "!"
                this@MainActivity.btInicio.text = "Jogar Novamente"
                this@MainActivity.llPalpite.visibility = View.INVISIBLE
                this@MainActivity.llInicio.visibility = View.VISIBLE
            }
        }
    }

    override fun onRestart() {
        super.onRestart()

        this@MainActivity.btInicio.callOnClick()
    }

}
