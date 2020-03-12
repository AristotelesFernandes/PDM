package com.example.popup

import android.app.AlertDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var btMensagem: Button
    private lateinit var btInput: Button
    private lateinit var btData: Button
    private lateinit var btHora: Button
    private lateinit var btFaixaValores: Button
    private lateinit var btEscolha: Button
    private lateinit var btUnico: Button
    private lateinit var btVarios: Button

    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btMensagem = findViewById(R.id.btMensagem)
        this.btMensagem.setOnClickListener{ mensagem() }

        this.btInput = findViewById(R.id.btInput)
        this.btInput.setOnClickListener{ input() }

        this.btData = findViewById(R.id.btData)
        this.btData.setOnClickListener{ data() }

        this.btHora = findViewById(R.id.btHora)
        this.btHora.setOnClickListener{ hora() }

        this.btFaixaValores = findViewById(R.id.btFaixaValores)
        this.btFaixaValores.setOnClickListener{ faixaValores() }

        this.btEscolha = findViewById(R.id.btEscolha)
        this.btEscolha.setOnClickListener{ escolha() }

        this.btUnico = findViewById(R.id.btUnico)
        this.btUnico.setOnClickListener{ unico() }

        this.btVarios = findViewById(R.id.btVarios)
        this.btVarios.setOnClickListener{ varios() }


    }

    fun mensagem() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Mensagem")
        builder.setIcon(R.mipmap.ic_launcher)
        builder.setMessage("Que bom!")
        builder.setPositiveButton("Ok"){ dialog, which ->
            Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }
        builder.setNeutralButton("Genérico") {dialog, which ->
            Toast.makeText(this, "Genérico", Toast.LENGTH_SHORT).show()
        }

        val janela = builder.create()
        janela.show()
    }

    fun input() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Input")
        builder.setMessage("Digite a mensagem")
        builder.setIcon(R.mipmap.ic_launcher)
        this.view = EditText(this)
        builder.setView(this.view)
        builder.setPositiveButton("Ok"){ dialog, which ->
            val msg = (this.view as EditText).text.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        val janela = builder.create()
        janela.show()
    }

    fun data() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Data")
        builder.setMessage("Informe uma data")
        builder.setIcon(R.mipmap.ic_launcher)

        this.view = DatePicker(this)
        builder.setView(this.view)
        builder.setPositiveButton("Ok"){ dialog, which ->
            val data = this.view as DatePicker
            val msg = "${data.dayOfMonth}/${data.month+1}/${data.year}"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        val janela = builder.create()
        janela.show()
    }

    fun hora() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Hora")
        builder.setMessage("Informe uma hora")
        builder.setIcon(R.mipmap.ic_launcher)

        this.view = TimePicker(this)
        val hora = this.view as TimePicker
        hora.setIs24HourView(true)
        builder.setView(this.view)
        builder.setPositiveButton("Ok"){ dialog, which ->

            val msg = if (Build.VERSION.SDK_INT >= 23) {
                "${hora.hour}:${hora.minute}"
            } else {
                "${hora.currentHour}:${hora.currentMinute}"
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        val janela = builder.create()
        janela.show()
    }

    fun faixaValores() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Faixa de Valores")
        builder.setMessage("Selecione um valor")
        builder.setIcon(R.mipmap.ic_launcher)

        this.view = SeekBar(this)
        val faixa = this.view as SeekBar
        faixa.max = 100

        builder.setView(this.view)
        builder.setPositiveButton("Ok"){ dialog, which ->
            val msg = faixa.progress.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        val janela = builder.create()
        janela.show()
    }

    fun escolha() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Escolha")
        builder.setMessage("Sim ou Não?")
        builder.setIcon(R.mipmap.ic_launcher)

        this.view = Switch(this)
        val escolha = this.view as Switch
        escolha.text = "Opção"

        builder.setView(this.view)
        builder.setPositiveButton("Ok"){ dialog, which ->
            val msg = if (escolha.isChecked) {
                "SIM"
            } else {
                "NÃO"
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        val janela = builder.create()
        janela.show()
    }

    fun unico () {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Único")
        builder.setMessage("Escolha apenas uma opção")
        builder.setIcon(R.mipmap.ic_launcher)

        this.view = RadioGroup(this)
        val grupo = this.view as RadioGroup

        for (i in 1..5) {
            val radio = RadioButton(this)
            radio.text = "Opção "+i.toString()
            grupo.addView(radio)
        }

        builder.setView(this.view)
        builder.setPositiveButton("Ok"){ dialog, which ->
            var msg = grupo.findViewById<RadioButton>(grupo.checkedRadioButtonId).text.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        val janela = builder.create()
        janela.show()
    }

    fun varios () {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Vários")
        builder.setMessage("Escolha as opções desejadas")
        builder.setIcon(R.mipmap.ic_launcher)

        this.view = LinearLayout(this)
        val layout = this.view as LinearLayout
        layout.orientation = LinearLayout.VERTICAL

        for (i in 1..5) {
            val check = CheckBox(this)
            check.text = "Opção "+i.toString()
            layout.addView(check)
        }

        builder.setView(this.view)
        builder.setPositiveButton("Ok"){ dialog, which ->
            var msg = ""

            for (i in 0..4){
                val check = layout.getChildAt(i) as CheckBox
                if (check.isChecked) {
                    if (msg != "")
                        msg += " - "
                    msg += check.text.toString()
                }
            }

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        val janela = builder.create()
        janela.show()
    }

}
