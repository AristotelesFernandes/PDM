package com.example.vaievolta

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var etMensagem: EditText
    private lateinit var btOk: Button
    private lateinit var btSobre: Button
    val OUTRA = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("APP_VAI_VOLTA","Main - OnCreate")

        setContentView(R.layout.activity_main)

        this.etMensagem = findViewById(R.id.etMainMensagem)

        this.btOk = findViewById(R.id.btMainOk)
        this.btOk.setOnClickListener(OnClickBotao())

        this.btSobre = findViewById(R.id.btMainSobre)
        this.btSobre.setOnClickListener(OnClickBotaoSobre())

    }

    override fun onStart() {
        super.onStart()
        Log.i("APP_VAI_VOLTA","Main - OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("APP_VAI_VOLTA","Main - OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("APP_VAI_VOLTA","Main - OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("APP_VAI_VOLTA","Main - OnStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("APP_VAI_VOLTA","Main - OnRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("APP_VAI_VOLTA","Main - OnDestroy")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == OUTRA){
                val msg = data?.getStringExtra("MENSAGEM_VOLTA")
                this.etMensagem.setText(msg)
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "Voltou pelo dispositivo", Toast.LENGTH_SHORT).show()
        }
    }

    inner class OnClickBotao(): View.OnClickListener {
        override fun onClick(v: View?) {
            val it = Intent(this@MainActivity, OutraActivity::class.java)
            val msg = etMensagem.text.toString()
            it.putExtra("MENSAGEM", msg)
            startActivityForResult(it, OUTRA)
        }
    }

    inner class OnClickBotaoSobre(): View.OnClickListener {
        override fun onClick(v: View?) {
            val it = Intent(this@MainActivity, SobreActivity::class.java)
            startActivity(it)
        }
    }

}
