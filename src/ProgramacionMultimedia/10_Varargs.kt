package ProgramacionMultimedia// Parámetros variables (varargs)

fun sumarTodos(vararg numeros: Int): Int {
    var suma = 0
    for (num in numeros) {
        suma += num
    }
    return suma
}

fun procesarDatos(prefijo: String, vararg valores: Int) {
    println(prefijo + ": " + valores.joinToString(", "))
}

fun main() {
    println(sumarTodos())
    println(sumarTodos(1, 2, 3))
    println(sumarTodos(1, 2, 3, 4, 5))
    
    val array = intArrayOf(10, 20, 30)
    println(sumarTodos(*array))
    
    procesarDatos("Resultado", 1, 2, 3, 4, 5)
}