package ProgramacionMultimedia// Usando Result<T>

fun parseNumero(texto: String): Result<Int> {
    return try {
        Result.success(texto.toInt())
    } catch (e: Exception) {
        Result.failure(e)
    }
}

fun main() {
    parseNumero("42")
        .onSuccess { numero -> println("OK: " + numero) }
        .onFailure { println("Error") }
    
    parseNumero("abc")
        .onSuccess { numero -> println("OK: " + numero) }
        .onFailure { println("Error")
    }
}