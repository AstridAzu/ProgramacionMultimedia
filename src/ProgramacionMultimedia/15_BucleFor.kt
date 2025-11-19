package ProgramacionMultimedia// Bucle for

fun main() {
    // Rango 1..5
    println("Rango 1..5:")
    for (i in 1..5) {
        println(i)
    }
    
    // Rango exclusivo
    println("\nRango 1..<5:")
    for (i in 1..<5) {
        println(i)
    }
    
    // Descendente
    println("\nDescendente 5 downTo 1:")
    for (i in 5 downTo 1) {
        println(i)
    }
    
    // Con paso
    println("\nCon paso 1..10 step 2:")
    for (i in 1..10 step 2) {
        println(i)
    }
    
    // Sobre lista
    println("\nSobre lista:")
    val nombres = listOf("Ana", "Carlos", "Maria")
    for (nombre in nombres) {
        println(nombre)
    }
    
    // Con indice
    println("\nCon indice:")
    for ((indice, nombre) in nombres.withIndex()) {
        println(indice.toString() + ": " + nombre)
    }
}