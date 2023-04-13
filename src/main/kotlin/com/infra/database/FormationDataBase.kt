package com.infra.database

import com.domain.Formation
import com.domain.dto.FormationDto
import com.domain.dto.UserDto
import com.domain.enums.Level

object FormationDataBase {
    private val formations : MutableSet<Formation> = mutableSetOf()
    fun addFunction(formation: Formation) : Boolean {
        return formations.add(formation)
    }

    fun getUserFormation(user: UserDto) : List<Formation>{

        return formations.filter { it.getUser(user) != null}
    }

    fun getFormationByLevel(level : Level) : List<Formation>{
        return formations.filter { it.level == level }
    }

    fun updateFormation(formation: FormationDto) : Boolean {
        getFormationById(formation.id)?.let {
            it.level = formation.level
            return true
        }
        return false
    }

    fun getNameAndId() : Map<String, String>{
        val names = mutableMapOf<String, String>()
        for(form in formations){
            names.put(form.name, form.id)
        }
        return names
    }

    fun getSize() : Int{
        return formations.size
    }

    fun getFormationById(id: String) : Formation{
        return formations.first { form ->  form.id == id }
    }
}