package ProgramacionMultimedia// Try-catch-finally

fun main() {
    try {
        val numero = "abc".toInt()
    } catch (e: NumberFormatException) {
        println("Error: no es un numero")
    }
    
    try {
        println("Abriendo archivo")
    } catch (e: Exception) {
        println("Error")
    } finally {
        println("Finalizando")
    }
    
    val numero: Int? = try {
        "42".toInt()
    } catch (e: Exception) {
        null
    }
    println("Numero: " + numero)
}