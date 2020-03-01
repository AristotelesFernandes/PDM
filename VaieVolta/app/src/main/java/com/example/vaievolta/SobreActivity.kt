package com.example.vaievolta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout

class SobreActivity : AppCompatActivity() {
    private lateinit var llTela: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("APP_VAI_VOLTA","Sobre - OnCreate")
        setContentView(R.layout.activity_sobre)

        llTela = findViewById(R.id.llTela)
        llTela.setOnClickListener(OnClickTela())

    }

    override fun onStart() {
        super.onStart()
        Log.w("APP_VAI_VOLTA","Sobre - OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.w("APP_VAI_VOLTA","Sobre - OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.w("APP_VAI_VOLTA","Sobre - OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w("APP_VAI_VOLTA","Sobre - OnStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.w("APP_VAI_VOLTA","Sobre - OnRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("APP_VAI_VOLTA","Sobre - OnDestroy")
    }

    inner class OnClickTela(): View.OnClickListener{
        override fun onClick(v: View?) {
            finish()
        }
    }
}
