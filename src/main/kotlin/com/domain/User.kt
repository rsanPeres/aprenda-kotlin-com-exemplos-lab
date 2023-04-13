package com.domain

import com.domain.enums.Level
import com.domain.enums.UserType

open class User(id : String, val name : String, val email : String, val password : String, val userType: UserType, level: Level) : EntityBase(id, level) {

    open fun changeLevel(level: Level){
        when(level){
            Level.DEFAULT -> this.level = Level.BASICO
            Level.BASICO -> this.level = Level.INTERMEDIARIO
            Level.INTERMEDIARIO -> this.level = Level.AVANCADO
            Level.AVANCADO -> println("Você chegou no máximo, Parabéns!!!")
        }
    }

}