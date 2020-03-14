package com.example.deuruim

class Cadastro {
    private var listaEventos: ArrayList<Evento>

    constructor() {
        this.listaEventos = ArrayList()
    }

    fun add(evento: Evento) {
        this.listaEventos.add(evento)
    }

    fun getLista(): ArrayList<Evento> {
        return this.listaEventos
    }

    fun count(): Int {
        return this.listaEventos.size
    }

}