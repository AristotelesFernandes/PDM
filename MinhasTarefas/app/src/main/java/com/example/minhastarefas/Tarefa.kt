package com.example.minhastarefas

class Tarefa {
    var id: Int
    var descricao: String
    var prioridade: Int
    var status: Int



    constructor(id: Int, descricao: String, prioridade: Int, status: Int) {
        this.id = id
        this.descricao = descricao
        this.prioridade = prioridade
        this.status = status
    }

    constructor(descricao: String, prioridade: Int, status: Int) {
        this.id = -1
        this.descricao = descricao
        this.prioridade = prioridade
        this.status = status
    }

    override fun toString(): String {
        var s = ""

        when(this.status) {
            0 ->
                s = "aberto"
            1 ->
                s = "executando"
            2 ->
                s = "concluído"
        }

        return "${this.descricao} - ${this.prioridade} - ${s}"
    }

}