package ProgramacionMultimedia// Operadores lógicos

fun main() {
    val edad = 25
    val tieneLicencia = true
    
    // AND
    if (edad >= 18 && tieneLicencia) {
        println("Puede conducir")
    }
    
    // OR
    val diaSemana = 6
    val esFinSemana = diaSemana == 6 || diaSemana == 7
    println("Es fin de semana: " + esFinSemana)
    
    // NOT
    val activo = false
    if (!activo) {
        println("Esta inactivo")
    }
    
    // IN
    val numero = 15
    if (numero in 10..20) {
        println("Esta en el rango")
    }
    
    val letra = 'a'
    if (letra !in 'a'..'z') {
        println("No es minuscula")
    } else {
        println("Es minuscula")
    }
}