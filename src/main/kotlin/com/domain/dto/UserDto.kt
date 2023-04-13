package com.domain.dto

import com.domain.Student
import com.domain.Teacher
import com.domain.User
import com.domain.enums.Level
import com.domain.enums.UserType
import com.infra.tools.IdGenerator
import com.infra.tools.StringValidator

class UserDto() {
    var name: String = String()
    var email : String = String()
    var password : String = String()
    var userType: UserType = UserType.DEFAULT
    var level: Level = Level.DEFAULT
    fun dtoToObject() : User {
        return when(userType){
            UserType.STUDENT -> Student(IdGenerator.idGenerate(), name, email, password, userType, level)
            UserType.THEACHER -> Teacher(IdGenerator.idGenerate(), name, email, password, userType, level)
            UserType.DEFAULT -> User(IdGenerator.idGenerate(), name, email, password, userType, level)
        }
    }

    fun validate() : Boolean{
        return StringValidator.validate(this.name) && StringValidator.validate(this.email) && StringValidator.validate(this.password)
    }

}