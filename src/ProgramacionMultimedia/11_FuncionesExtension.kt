package ProgramacionMultimedia// Funciones de extensión

fun String.repetir(veces: Int): String {
    return this.repeat(veces)
}

fun Int.esPositivo(): Boolean = this > 0
fun Int.doble(): Int = this * 2

fun <T> List<T>.segundo(): T? {
    return if (this.size >= 2) this[1] else null
}

fun main() {
    println("Hola".repetir(3))
    
    println(5.esPositivo())
    println((-3).esPositivo())
    println(7.doble())
    
    println(listOf("a", "b", "c").segundo())
    println(listOf(1, 2).segundo())
}