package com.infra.tools

import com.domain.Course
import com.domain.Teacher
import com.domain.dto.FormationDto
import com.domain.dto.UserDto
import com.domain.enums.Level
import com.domain.enums.UserType
import com.infra.database.FormationDataBase
import java.util.*

object Menu {
    val sc = Scanner(System. `in`)

    fun Menu(){
        println("---------------- DIO ----------------")
        println("0 - Sair")
        println("1 - Ver nome e id de formações")
        println("2 - Cadastrar Formação")
        println("3 - Atualizar Formação")
        println("4 - Registrar cursos na formação")
        println("5 - Ver formações por nível")
        println("6 - Cadastrar aluno")
        println("7 - Ver formações do aluno")
    }

    fun getFormations(){
        if(FormationDataBase.getSize() > 0){
            val formations = FormationDataBase.getNameAndId()
            for(form in formations){
                println("Nome : ${form.key} Id: ${form.value}")
                for(cursos in FormationDataBase.getFormationById(form.value).getCorses()){
                    println("Nome do curso ${cursos.key} e professor: ${cursos.value}")
                }
            }
        }else{
            println("Não existem formações cadastradas!")
        }
    }

    fun cadastrarFormacao(){

        val dto = FormationDto()

        println("Digite o nome: ")
        dto.name = sc.nextLine()

        println("digite o level: ")
        var level = sc.nextLine()
        dto.level = Level.valueOf(level.uppercase())

        if(dto.validate()) FormationDataBase.addFunction(dto.dtoToObject()) else println("Formação Invalida")
    }

    fun update() {
        println("Digite o id: ")
        val dto = FormationDto()
        dto.id = sc.nextLine()
        sc.nextLine()

        println("Digite o novo level: ")
        var level = sc.nextLine()
        dto.level = Level.valueOf(level.uppercase())

        if (IdValidator.idValide(dto.id)) {
            if (FormationDataBase.updateFormation(dto)) println("Formação atualizada") else println("Formação não pode ser atualizada")

        }else{
            println("Id invalido")
        }
    }

    fun cadastrarAluno(){
        val dto = registrarUsuario()

        if(dto.validate()){
            println("Digite o id da formação que deseja iniciar: ")
            val form = sc.nextLine()
            if(IdValidator.idValide(form)) {
                    FormationDataBase.getFormationById(form)?.let{
                        if(it.registerUser(dto.dtoToObject()) == true) println("Registrado com sucesso") else println("Usuário não pode ser registrado")
                    }
                }else{
                    println("Id invalido")
            }
        }else{
            println("Usuario Invalido")
        }
    }

    fun verFormaçõesDoAluno(){
        val dto = UserDto()
        println("Digite o email do aluno: ")
        dto.email = sc.nextLine()

        println("Digite a senha do aluno: ")
        dto.password = sc.nextLine()

        val form = FormationDataBase.getUserFormation(dto)
        if(form.isNotEmpty()){
            for(f in form){
                println("Formação: ${f.name}, nível: ${f.level}")
                println("Cursos: ")
                for(curso in f.getCorses()){
                    println("Nome ${curso.key} professor: ${curso.value}")
                }
            }
        }else{
            println("O aluno não tem formações")
        }
    }

    fun registrarCursos(){
        println("Digite o id da formação: ")
        val id = sc.nextLine()
        if(IdValidator.idValide(id)){
            FormationDataBase.getFormationById(id)?.let {
                val prof = registrarUsuario().dtoToObject()
                val curso = registrarCurso()
                it.registerCorses(prof, curso)
            }
        }else{
            println("Id invalido")
        }
    }

    fun registrarUsuario() : UserDto {
        val dto = UserDto()
        println("Digite o nome do usuário: ")
        dto.name = sc.nextLine()

        println("Digite o email: ")
        dto.email = sc.nextLine()

        println("Digite a senha: ")
        dto.password = sc.nextLine()

        println("Digite o tipo de usuário")
        dto.userType = UserType.valueOf(sc.nextLine().uppercase())

        return dto
    }

    fun registrarCurso() : Course {
        println("Digite o nome curso: ")
        val name = sc.nextLine()

        println("Digite o nível: ")
        val nivel = sc.nextLine().uppercase()

        println("Digite as horas: ")
        val hours = sc.nextInt()
        return Course(IdGenerator.idGenerate(), Level.valueOf(nivel), name, hours)
    }

    fun porLevel(){
        println("Digite o nível da formação")
        val level = sc.nextLine().uppercase()
        val form = FormationDataBase.getFormationByLevel(Level.valueOf(level))
        if(form.isNotEmpty()){
            println("Formações de nível ${level.lowercase()}")
            for(f in form){
                println("Nome ${f.name} ${f.id}")
            }
        }else{
            println("Não existem formações cadastradas")
        }
    }
}