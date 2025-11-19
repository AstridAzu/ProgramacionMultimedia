package ProgramacionMultimedia// Parámetros por defecto

fun crearPersona(nombre: String, edad: Int = 0, ciudad: String = "Desconocida") {
    println("\$nombre, \$edad anos, vive en \$ciudad")
}

fun main() {
    crearPersona("Ana")
    crearPersona("Carlos", 30)
    crearPersona("Maria", 25, "Barcelona")
    crearPersona("Juan", ciudad = "Madrid")
    crearPersona(nombre = "Rosa", edad = 35)
}