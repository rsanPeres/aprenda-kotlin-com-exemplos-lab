package com.infra.tools

object StringValidator {
    fun validate(str : String) : Boolean{
        return str.length >= 3
    }
}