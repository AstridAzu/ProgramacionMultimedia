package ProgramacionMultimedia

class Ejercicio1 {
    fun main() {
        println("cual es tu nombre")
        //
        val  nombre = readLine() ?:"visitantes"

        val horaActual = java.time.LocalDateTime.now().hour
        //funcion a lo lima
        val actual = when{
            horaActual > 18 ->"buenos dias"
            horaActual > 12 ->"buenas tardes"
            else ->"buenos noches"
        }
        //resultados del usuario
        println("$nombre $actual")
        println()

    }
}
