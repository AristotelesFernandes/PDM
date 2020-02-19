package com.example.expobre

import java.util.*

object Megasena {

    fun getInstance(): Set<String> {
        val random = Random()
        var numeros = mutableSetOf<String>()

        //Preenchendo a lista de 6 números com a formatação 00 (dois dígitos)
        while (numeros.size < 6) {
            numeros.add(String.format("%02d", random.nextInt(60)+1))
        }

        return numeros.toSortedSet()
    }

}