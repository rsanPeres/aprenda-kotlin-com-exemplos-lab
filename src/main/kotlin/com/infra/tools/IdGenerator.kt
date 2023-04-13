package com.infra.tools

object IdGenerator {
    fun idGenerate() : String{
        val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return List(20) { alphabet.random() }.joinToString("")
    }
}