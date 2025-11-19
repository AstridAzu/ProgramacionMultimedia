package ProgramacionMultimedia// Estructura if-else

fun main() {
    val edad = 25
    
    // if simple
    if (edad >= 18) {
        println("Eres mayor de edad")
    }
    
    // if-else
    if (edad >= 18) {
        println("Mayor de edad")
    } else {
        println("Menor de edad")
    }
    
    // if-else if-else
    val nota = 8.5
    if (nota >= 9) {
        println("Sobresaliente")
    } else if (nota >= 7) {
        println("Notable")
    } else if (nota >= 5) {
        println("Aprobado")
    } else {
        println("Suspenso")
    }
    
    // if como expresion
    val resultado = if (edad >= 18) "Mayor" else "Menor"
    println("Categoria: " + resultado)
}