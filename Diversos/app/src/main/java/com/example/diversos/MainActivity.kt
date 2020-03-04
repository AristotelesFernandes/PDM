package com.example.diversos

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.transition.Slide
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var btHTML: Button
    private lateinit var btDiscar: Button
    private lateinit var btLigar: Button
    private lateinit var btCompartilhar: Button
    private lateinit var btEmail: Button
    private lateinit var btPontoMapa: Button
    private lateinit var btRotaMapa: Button
    private lateinit var btSMS: Button
    private lateinit var btYoutube: Button
    private lateinit var btFoto: Button

    private val RC_FOTO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btHTML = findViewById(R.id.btHTML)
        this.btHTML.setOnClickListener({ html() })

        this.btDiscar = findViewById(R.id.btDiscar)
        this.btDiscar.setOnClickListener({ discar() })

        this.btLigar = findViewById(R.id.btLigar)
        this.btLigar.setOnClickListener({ ligar() })

        this.btCompartilhar = findViewById(R.id.btCompartilhar)
        this.btCompartilhar.setOnClickListener({ compartilhar() })

        this.btEmail = findViewById(R.id.btEmail)
        this.btEmail.setOnClickListener({ email() })

        this.btPontoMapa = findViewById(R.id.btPontoMapa)
        this.btPontoMapa.setOnClickListener({ pontoMapa() })

        this.btRotaMapa = findViewById(R.id.btRotaMapa)
        this.btRotaMapa.setOnClickListener({ rotaMapa() })

        this.btSMS = findViewById(R.id.btSMS)
        this.btSMS.setOnClickListener({ sms() })

        this.btYoutube = findViewById(R.id.btYoutube)
        this.btYoutube.setOnClickListener({ youtube() })

        this.btFoto = findViewById(R.id.btFoto)
        this.btFoto.setOnClickListener({ foto() })

    }

    fun html() {
        val uri = Uri.parse("http://www.ifpb.edu.br")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun discar() {
        val uri = Uri.parse("tel:36121392")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        startActivity(intent)
    }

    fun ligar() {
        val uri = Uri.parse("tel:36121392")
        val intent = Intent(Intent.ACTION_CALL, uri)
        val call = Manifest.permission.CALL_PHONE
        val granted = PackageManager.PERMISSION_GRANTED
        if (ContextCompat.checkSelfPermission(this, call) == granted){
            startActivity(intent)
        } else {
            Toast.makeText(this, "Não foi possível efetuar a ligação.", Toast.LENGTH_SHORT).show()
        }
    }

    fun compartilhar() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar")
        // intent.setPackage("com.whatsapp")
        if (intent.resolveActivity(packageManager) != null){
            startActivity(Intent.createChooser(intent, "Compartilhar"))
        }
    }

    fun email() {
        val uri = Uri.parse("mailto:valeria.cavalcanti@ifpb.edu.br")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Assunto")
        intent.putExtra(Intent.EXTRA_TEXT, "Texto")
        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun pontoMapa() {
        val uri = Uri.parse("geo:-7.1356496,-34.8760932")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun rotaMapa() {
        val origem = "-7.1356496,-34.8760932"
        val destino = "-7.1181836,-34.8730402"
        val url = "http://maps.google.com/maps"
        val uri = Uri.parse("${url}?f=&saddr=${origem}+&daddr=${destino}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun sms() {
        val uri = Uri.parse("sms:36121392")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", "Mensagem")
        startActivity(intent)
    }

    fun youtube() {
        val uri = Uri.parse("vnd.youtube://dglqGGyWbVo")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun foto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, RC_FOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RC_FOTO) {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                createPopUpWindow(imageBitmap)
            }
        }
    }

    private fun createPopUpWindow(imageBitmap: Bitmap) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.activity_popup, null)

            val popupWindow = PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.LEFT
                popupWindow.enterTransition = slideIn

                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut

            }

            val ivImagem = view.findViewById<ImageView>(R.id.ivPUImagem)
            val buttonPopup = view.findViewById<Button>(R.id.btPUFechar)

            ivImagem.setImageBitmap(imageBitmap)
            buttonPopup.setOnClickListener {
                popupWindow.dismiss()
            }

            popupWindow.setOnDismissListener {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

            TransitionManager.beginDelayedTransition(llMainLayout)
            popupWindow.showAtLocation(llMainLayout, Gravity.CENTER,0,0)

        } else {
            Toast.makeText(this, "Versão não suportada!", Toast.LENGTH_SHORT).show()
        }
    }

}
