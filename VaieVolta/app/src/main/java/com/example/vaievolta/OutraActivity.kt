package com.example.vaievolta

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class OutraActivity : AppCompatActivity() {
    private lateinit var etOutraMensagem: EditText
    private lateinit var btOutraOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("APP_VAI_VOLTA","Outra - OnCreate")

        setContentView(R.layout.activity_outra)

        this.etOutraMensagem = findViewById(R.id.etOutraMensagem)
        this.etOutraMensagem.setText(intent.getStringExtra("MENSAGEM"))

        this.btOutraOk = findViewById(R.id.btOutraOk)
        this.btOutraOk.setOnClickListener(OnClickBotao())

    }

    override fun onStart() {
        super.onStart()
        Log.e("APP_VAI_VOLTA","Outra - OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("APP_VAI_VOLTA","Outra - OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("APP_VAI_VOLTA","Outra - OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("APP_VAI_VOLTA","Outra - OnStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("APP_VAI_VOLTA","Outra - OnRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("APP_VAI_VOLTA","Outra - OnDestroy")
    }

    inner class OnClickBotao(): View.OnClickListener{
        override fun onClick(v: View?) {
            val it = Intent()
            val msg = this@OutraActivity.etOutraMensagem.text.toString()
            it.putExtra("MENSAGEM_VOLTA", msg)
            setResult(Activity.RESULT_OK, it)
            finish()
        }
    }
}
