package com.example.minhascorespreferidas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import layout.CorPreferida

class MainActivity : AppCompatActivity() {
    private val APP_CORES = "APP_CORES"
    val FORM_CADASTRO = 2

    private lateinit var lvMainCoresPreferidas: ListView
    private lateinit var btMainAdicionarCor: Button

    private lateinit var listaCores: ArrayList<CorPreferida>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.lvMainCoresPreferidas = findViewById(R.id.lvMainCoresPreferidas)
        this.btMainAdicionarCor = findViewById(R.id.btMainAdicionarCor)

        this.listaCores = ArrayList<CorPreferida>()
        this.lvMainCoresPreferidas.adapter = AdapterCoresPreferidas(this.listaCores, this)
        this.lvMainCoresPreferidas.setOnItemLongClickListener(OnLongClickItemList())
        this.btMainAdicionarCor.setOnClickListener(OnClickBotaoAdicionar())

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == FORM_CADASTRO) {

                val corPreferida = data?.getParcelableExtra<CorPreferida>("COR")

                this.listaCores.add(corPreferida!!)
                atualizaLista()
                Log.i(APP_CORES, "Cor cadastrada: - ${corPreferida.toString()}")

            }
        }
    }

    inner class OnClickBotaoAdicionar : View.OnClickListener {
        override fun onClick(v: View?) {

            val it = Intent(this@MainActivity, CadastroActivity::class.java)
            startActivityForResult(it, FORM_CADASTRO)
        }
    }

    inner class OnLongClickItemList : AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {

            this@MainActivity.listaCores.removeAt(position)
            atualizaLista()
            Toast.makeText(this@MainActivity, "Cor removida com sucesso!", Toast.LENGTH_LONG).show()

            return true
        }
    }

    private fun atualizaLista(){
        (this.lvMainCoresPreferidas.adapter as AdapterCoresPreferidas).notifyDataSetChanged()
    }

}