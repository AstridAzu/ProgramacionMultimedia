package ProgramacionMultimedia// Bucle while y do-while

fun main() {
    // While
    println("While:")
    var contador = 0
    while (contador < 5) {
        println(contador)
        contador++
    }
    
    // Do-while
    println("\nDo-while:")
    var x = 0
    do {
        println("x = " + x)
        x++
    } while (x < 3)
    
    // Lectura de datos
    println("\nWhile con iterador:")
    val lineas = mutableListOf<String>()
    val entrada = listOf("linea1", "linea2", "linea3").iterator()
    while (entrada.hasNext()) {
        lineas.add(entrada.next())
    }
    println("Lineas: " + lineas)
}