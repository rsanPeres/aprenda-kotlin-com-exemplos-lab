package com.domain

import com.domain.dto.UserDto
import com.domain.enums.Level
import com.domain.enums.UserType

class Formation(id : String, val name : String, level: Level) : EntityBase(id, level) {
    private val subscribed = mutableListOf<User>()
    private val courses = mutableMapOf<Course,Teacher>()

    fun registerUser(user : User) : Boolean{
        if(subscribed.none { us -> us.email == user.email && us.password == user.password }){
            changeUserLevel(user)
            return subscribed.add(user)
        }
        return false
    }

    fun registerCorses(user: User, course: Course){
        if(user.userType == UserType.THEACHER) courses.put(course, user as Teacher) else println("Usuário não é professor")
    }

    fun getUser(user : UserDto) : User?{
        return subscribed?.first { us -> us.email == user.email && us.password == user.email }
    }

    fun changeUserLevel(user : User){
        if(user.level != level){
            user.changeLevel(level)
        }
    }

    fun getCorses() : Map<String, String>{
        val cursos = mutableMapOf<String, String>()
        courses.forEach{ curso -> cursos.put(curso.key.name, curso.value.name)}
        return cursos
    }
}