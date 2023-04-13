package com.domain.dto

import com.domain.Formation
import com.domain.enums.Level
import com.infra.tools.IdGenerator
import com.infra.tools.StringValidator
import com.infra.tools.StringValidator.validate

class FormationDto() {
    var id : String = String()
    var name : String = String()
    var level : Level = Level.DEFAULT
    fun dtoToObject() : Formation{
        return Formation(IdGenerator.idGenerate(), this.name, this.level)
    }
    fun validate() : Boolean{
        return (StringValidator.validate(this.name))
    }
}