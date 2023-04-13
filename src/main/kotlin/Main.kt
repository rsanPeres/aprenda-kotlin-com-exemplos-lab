import com.domain.dto.FormationDto
import com.domain.enums.Level
import com.infra.database.FormationDataBase
import com.infra.tools.Menu
import com.infra.tools.StringValidator
import com.infra.tools.StringValidator.validate
import java.util.Scanner

fun main(args: Array<String>) {
    val sc = Scanner(System. `in`)

    Menu.Menu()

    var op = sc.nextInt()

    while (op != 0){
        when(op){
            1 -> Menu.getFormations()
            2 -> Menu.cadastrarFormacao()
            3 -> Menu.update()
            4 -> Menu.registrarCursos()
            5 -> Menu.porLevel()
            6 -> Menu.cadastrarAluno()
            7 -> Menu.verFormaçõesDoAluno()
        }
        Menu.Menu()
        op = sc.nextInt()
    }
    sc.close()
}


