package com.example.autentica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class NovaTelaActivity : AppCompatActivity() {
    private lateinit var btVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_tela)

        btVoltar = findViewById(R.id.ntaBtVoltar)

        btVoltar.setOnClickListener(OnClickBotaoVoltar())

    }

    inner class OnClickBotaoVoltar : View.OnClickListener {
        override fun onClick(v: View?) {
            finish()
        }
    }
}
