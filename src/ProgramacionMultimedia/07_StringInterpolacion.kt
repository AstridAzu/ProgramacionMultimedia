package ProgramacionMultimedia// String con interpolación

fun main() {
    val mensaje = "Hola Kotlin"
    
    val nombre = "Carlos"
    val edad = 28
    val presentacion = "Me llamo \$nombre y tengo \$edad anos"
    println(presentacion)
    
    val a = 5
    val b = 3
    val resultado = "La suma de \$a + \$b es \${a + b}"
    println(resultado)
    
    val lista = listOf(1, 2, 3)
    val descripcion = "La lista tiene \${lista.size} elementos"
    println(descripcion)
}