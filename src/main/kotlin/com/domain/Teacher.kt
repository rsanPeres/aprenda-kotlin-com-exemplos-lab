package com.domain

import com.domain.enums.Level
import com.domain.enums.UserType

class Teacher(id : String, name : String, email : String, password : String, userType: UserType, level: Level) : User(id, name, email, password, userType, level){

}