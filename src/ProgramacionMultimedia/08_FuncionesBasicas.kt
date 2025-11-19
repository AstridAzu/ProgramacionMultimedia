package ProgramacionMultimedia// Funciones básicas

fun sumar(a: Int, b: Int): Int {
    return a + b
}

fun multiplicar(a: Int, b: Int): Int = a * b

fun obtenerSaludo(): String = "Hola a todos"

fun saludar(nombre: String) {
    println("Hola \$nombre")
}

fun obtenerPI(): Double = 3.14159

fun main() {
    println("Suma: \${sumar(5, 3)}")
    println("Multiplicacion: \${multiplicar(4, 7)}")
    println(obtenerSaludo())
    saludar("Maria")
    println("PI: \${obtenerPI()}")
}