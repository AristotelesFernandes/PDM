package com.example.deuruim

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.core.view.drawToBitmap

class FormActivity : AppCompatActivity() {
    private lateinit var etDescricao: EditText
    private lateinit var sbNota: SeekBar
    private lateinit var btFoto: Button
    private lateinit var ivFoto: ImageView
    private lateinit var btSalvar: Button
    private lateinit var btCancelar: Button

    private val RC_CAMERA = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        this.etDescricao = findViewById(R.id.etFormDescricao)

        this.sbNota = findViewById(R.id.sbFormNota)

        this.btFoto = findViewById(R.id.btFormFoto)
        this.btFoto.setOnClickListener({ tirarFoto() })

        this.ivFoto = findViewById(R.id.ivFormFoto)

        this.btSalvar = findViewById(R.id.btFormSalvar)
        this.btSalvar.setOnClickListener({ salvar() })

        this.btCancelar = findViewById(R.id.btFormCancelar)
        this.btCancelar.setOnClickListener({
            finish()
        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RC_CAMERA) {
                val imagem = data?.extras?.get("data") as Bitmap
                this.ivFoto.setImageBitmap(imagem)
            }
        }
    }

    fun tirarFoto() {
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera, RC_CAMERA)
    }

    fun salvar() {
        val descricao = this.etDescricao.text.toString()
        val nota = this.sbNota.progress

        val foto = if (ivFoto.drawable != null)
            this.ivFoto.drawToBitmap()
        else
            null

        if (descricao == "") {
            Toast.makeText(this, "Descrição obrigatória!", Toast.LENGTH_SHORT).show()
        } else {

            val evento = Evento(descricao, foto, nota)
            val intent = Intent()
            intent.putExtra("EVENTO", evento)
            setResult(Activity.RESULT_OK, intent)

            finish()
        }
    }

}
