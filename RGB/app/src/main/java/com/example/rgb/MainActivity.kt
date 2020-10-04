package com.example.rgb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var tvMainVermelho: TextView
    private lateinit var tvMainVerde: TextView
    private lateinit var tvMainAzul: TextView

    private var texto: String? = ""

    private lateinit var telaReceiver: TelaReceiver
    private lateinit var telaIntentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.telaReceiver = TelaReceiver()
        this.telaIntentFilter = IntentFilter()
        this.telaIntentFilter.addAction(Intent.ACTION_USER_PRESENT)

        this.tvMainVermelho = findViewById(R.id.tvMainVermelho)
        this.tvMainVerde = findViewById(R.id.tvMainVerde)
        this.tvMainAzul = findViewById(R.id.tvMainAzul)

        this.texto = intent.dataString

        if (this.texto != null) {
            this.tvMainVermelho.text = this.texto
            this.tvMainVerde.text = this.texto
            this.tvMainAzul.text = this.texto
        } else {
            this.tvMainVermelho.text = "VERMELHO"
            this.tvMainVerde.text = "VERDE"
            this.tvMainAzul.text = "AZUL"
        }

        this.tvMainVermelho.setTextColor(Color.parseColor("#FF0000"))
        this.tvMainVerde.setTextColor(Color.parseColor("#00FF00"))
        this.tvMainAzul.setTextColor(Color.parseColor("#0000FF"))

    }


    override fun onResume() {
        super.onResume()
        registerReceiver(this.telaReceiver, this.telaIntentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(this.telaReceiver)
    }

    private inner class TelaReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            this@MainActivity.tvMainVermelho.text = "VERMELHO"
            this@MainActivity.tvMainVerde.text = "VERDE"
            this@MainActivity.tvMainAzul.text = "AZUL"
            this@MainActivity.tvMainVermelho.setTextColor(Color.parseColor("#FF0000"))
            this@MainActivity.tvMainVerde.setTextColor(Color.parseColor("#00FF00"))
            this@MainActivity.tvMainAzul.setTextColor(Color.parseColor("#0000FF"))
        }

    }

}