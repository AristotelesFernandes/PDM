package com.example.minhastarefas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context): SQLiteOpenHelper(context,"tarefas.db",null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table if not exists tarefa (" +
                " id integer primary key autoincrement," +
                " descricao text," +
                " prioridade integer," +
                " status integer)"

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, before: Int, actual: Int) {
        db?.execSQL("drop table tarefa")
        this.onCreate(db)
    }

}