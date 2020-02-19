package com.example.expobre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvNumeros: TextView
    private lateinit var btGerarNumeros: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Vinculando os campos às variáveis para manipulação
        tvNumeros = findViewById(R.id.tvNumeros)
        btGerarNumeros = findViewById(R.id.btGerarNumeros)

        //Executando o método para gerar números e exibi-los
        btGerarNumeros.setOnClickListener{tvNumeros.text = Megasena.getInstance().joinToString(" ")}
    }
}
