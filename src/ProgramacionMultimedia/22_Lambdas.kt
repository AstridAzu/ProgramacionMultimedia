package ProgramacionMultimedia// Funciones Lambda

fun main() {
    val sumar: (Int, Int) -> Int = { a, b -> a + b }
    println("Suma: " + sumar(5, 3))
    
    val doble: (Int) -> Int = { it * 2 }
    println("Doble de 5: " + doble(5))
    
    val saludo: () -> String = { "Hola Kotlin" }
    println(saludo())
    
    val calcular: (Int, Int) -> Int = { a, b ->
        val suma = a + b
        suma * 2
    }
    println("Calculo(3, 4): " + calcular(3, 4))
}