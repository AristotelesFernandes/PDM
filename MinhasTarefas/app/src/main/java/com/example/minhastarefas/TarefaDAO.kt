package com.example.minhastarefas

import android.content.ContentValues
import android.content.Context

class TarefaDAO {

    private var bancoHelper: BancoHelper

    constructor(contexto: Context){
        this.bancoHelper = BancoHelper(contexto)
    }

    fun add(tarefa: Tarefa){
        val cv = ContentValues()
        cv.put("descricao", tarefa.descricao)
        cv.put("prioridade", tarefa.prioridade)
        cv.put("status", tarefa.status)

        this.bancoHelper.writableDatabase.insert("tarefa", null, cv)
    }

    fun count(): Int{
        val colunas = arrayOf("id")
        val banco = this.bancoHelper.readableDatabase
        val c = banco.query("pessoas", colunas, null, null, null, null, null)

        return c.count
    }

    fun selectOne(id: Int): Tarefa{
        val banco = this.bancoHelper.readableDatabase
        val where = "id = ?"
        val wherep = arrayOf(id.toString())
        val colunas = arrayOf("id", "descricao", "prioridade", "status")
        val c = banco.query("tarefa", colunas, where, wherep, null, null,null)

        c.moveToFirst()

        var descricao = c.getString(c.getColumnIndex("descricao"))
        var prioridade = c.getInt(c.getColumnIndex("prioridade"))
        var status = c.getInt(c.getColumnIndex("status"))
        var tarefa = Tarefa(id, descricao, prioridade, status)


        return tarefa
    }

    fun selectAll(): ArrayList<Tarefa>{
        val lista = arrayListOf<Tarefa>()
        val banco = this.bancoHelper.readableDatabase
        val colunas = arrayOf("id", "descricao", "prioridade", "status")
        val c = banco.query("tarefa", colunas, null, null, null, null,null)

        c.moveToFirst()
        if (c.count != 0) {
            do {
                var id = c.getInt(c.getColumnIndex("id"))
                var descricao = c.getString(c.getColumnIndex("descricao"))
                var prioridade = c.getInt(c.getColumnIndex("prioridade"))
                var status = c.getInt(c.getColumnIndex("status"))
                lista.add(Tarefa(id, descricao, prioridade, status))
            } while (c.moveToNext())
        }
        return lista
    }

    fun update(tarefa: Tarefa){
        val cv = ContentValues()
        val where = "id = ?"
        val wherep = arrayOf(tarefa.id.toString())
        cv.put("descricao", tarefa.descricao)
        cv.put("prioridade", tarefa.prioridade)
        cv.put("status", tarefa.status)
        this.bancoHelper.writableDatabase.update("tarefa", cv, where, wherep)

    }

    fun delete(tarefa: Tarefa){
        val where = "id = ?"
        val wherep = arrayOf(tarefa.id.toString())
        this.bancoHelper.writableDatabase.delete("tarefa", where, wherep)

    }



}