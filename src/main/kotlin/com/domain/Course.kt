package com.domain

import com.domain.enums.Level

class Course(id : String, level: Level, val name : String, val hours : Int) : EntityBase(id, level) {
}