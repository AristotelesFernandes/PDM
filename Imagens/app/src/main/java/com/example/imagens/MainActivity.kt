package com.example.imagens

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var btCamera: Button
    private lateinit var btArquivo: Button
    private lateinit var btDownload: Button
    private lateinit var ivImagem: ImageView

    private val RC_CAMERA = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btCamera = findViewById(R.id.btCamera)
        this.btCamera.setOnClickListener({ camera() })

        this.btArquivo = findViewById(R.id.btArquivo)
        this.btArquivo.setOnClickListener( {arquivo() })

        this.btDownload = findViewById(R.id.btDownload)
        this.btDownload.setOnClickListener({ download() })

        this.ivImagem = findViewById(R.id.ivImagem)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == RC_CAMERA) {
                val imagem = data?.extras?.get("data") as Bitmap
                this.ivImagem.setImageBitmap(imagem)
            }
        } else {
            Toast.makeText(this, "Operação cancelada pelo usuário", Toast.LENGTH_SHORT).show()
        }
    }

    fun camera() {
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera, RC_CAMERA)
    }

    fun arquivo() {
        this.ivImagem.setImageResource(R.drawable.super_mario)
    }

    fun downloadDaImagem(url: String): Bitmap {
        URL(url).openStream().use {
            val imagem = BitmapFactory.decodeStream(it)
            return imagem
        }
    }

    fun download() {
        var posicao = -1
        val builder = AlertDialog.Builder(this@MainActivity)
        val lista = arrayOf("ldpi", "mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi")

        builder.setTitle("Resolução da imagem")
        builder.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(applicationContext,"Donwload cancelado.", Toast.LENGTH_SHORT).show()
        }
        builder.setPositiveButton("Ok") { _, _ ->
            if (posicao == -1) {
                Toast.makeText(applicationContext,"Nenhuma opção foi selecionada.", Toast.LENGTH_SHORT).show()
            } else {

            var url = "http://www.valeria.eti.br/sm/"
            when(posicao) {
                0 -> url += "sm_ldpi.png"
                1 -> url += "sm_mdpi.png"
                2 -> url += "sm_hdpi.png"
                3 -> url += "sm_xhdpi.png"
                4 -> url += "sm_xxhdpi.png"
                5 -> url += "sm_xxxhdpi.png"
            }

            val handler = Handler()
            Thread {
                val imagem = this.downloadDaImagem(url)

                handler.post {
                    this.ivImagem.setImageBitmap(imagem)
                }
            }.start()

            }
        }

        builder.setSingleChoiceItems(lista, -1) { _, i ->
            posicao = i
        }

        val janela: AlertDialog = builder.create()
        janela.show()
    }

}
