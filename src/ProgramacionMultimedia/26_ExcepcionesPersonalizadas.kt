package ProgramacionMultimedia// Excepciones personalizadas

class DatosInvalidosException(mensaje: String) : Exception(mensaje)

fun validarEdad(edad: Int) {
    if (edad < 0) {
        throw DatosInvalidosException("Edad negativa")
    }
    if (edad > 150) {
        throw DatosInvalidosException("Edad irreal")
    }
    println("Edad valida: " + edad)
}

fun main() {
    try {
        validarEdad(25)
    } catch (e: DatosInvalidosException) {
        println("Error: " + e.message)
    }
    
    try {
        validarEdad(-5)
    } catch (e: DatosInvalidosException) {
        println("Error: " + e.message)
    }
}