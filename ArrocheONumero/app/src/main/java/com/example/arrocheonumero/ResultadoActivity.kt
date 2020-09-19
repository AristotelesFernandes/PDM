package com.example.arrocheonumero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultadoActivity : AppCompatActivity() {
    private lateinit var etResultado1: TextView
    private lateinit var etResultado2: TextView
    private lateinit var btJogarNovamente: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        this.etResultado1 = findViewById(R.id.tvResultado1)
        this.etResultado2 = findViewById(R.id.tvResultado2)
        this.btJogarNovamente = findViewById(R.id.btJogarNovamente)

        this.etResultado1.text = intent.getStringExtra("RESULTADO")
        this.etResultado2.text = intent.getStringExtra("MENSAGEM")

        this.btJogarNovamente.setOnClickListener(OnClickBotaoJogarNovamente())
    }

    inner class OnClickBotaoJogarNovamente : View.OnClickListener {
        override fun onClick(v: View?) {
            finish()
        }
    }

}