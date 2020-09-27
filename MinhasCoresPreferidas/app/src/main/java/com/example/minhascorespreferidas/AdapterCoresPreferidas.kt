package com.example.minhascorespreferidas

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import layout.CorPreferida

class AdapterCoresPreferidas(private var cores: ArrayList<CorPreferida>, var context: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val cor = this.cores.get(position)
        val linha : View
        if (convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            linha = inflater.inflate(R.layout.lista_cor_personalizada, null)
        }else{
            linha = convertView
        }
        val tvCorPersonalizada = linha.findViewById<TextView>(R.id.tvCorPersonalizada)
        tvCorPersonalizada.text = cor.nome
        tvCorPersonalizada.setBackgroundColor(Color.parseColor(cor.codigo))
        return linha
    }

    override fun getItem(position: Int): Any {
        return this.cores.get(position)
    }
    override fun getItemId(position: Int): Long {
        return -1
    }
    override fun getCount(): Int {
        return this.cores.count()
    }
}