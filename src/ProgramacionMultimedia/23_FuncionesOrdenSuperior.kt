package ProgramacionMultimedia// Funciones de orden superior

fun aplicarOperacion(a: Int, b: Int, operacion: (Int, Int) -> Int): Int {
    return operacion(a, b)
}

fun crearMultiplicador(factor: Int): (Int) -> Int {
    return { numero -> numero * factor }
}

fun main() {
    val r1 = aplicarOperacion(5, 3) { x, y -> x + y }
    println("Suma: " + r1)
    
    val duplicar = crearMultiplicador(2)
    println("Duplicar 5: " + duplicar(5))
}