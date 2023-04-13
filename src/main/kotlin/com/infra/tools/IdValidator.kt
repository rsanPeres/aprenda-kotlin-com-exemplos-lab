package com.infra.tools

object IdValidator {
    fun idValide(string: String) : Boolean{
        return string.length == 20
    }
}