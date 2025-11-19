package ProgramacionMultimedia// Estructura when

fun obtenerDia(numero: Int): String = when (numero) {
    1 -> "Lunes"
    2 -> "Martes"
    3 -> "Miercoles"
    4 -> "Jueves"
    5 -> "Viernes"
    6 -> "Sabado"
    7 -> "Domingo"
    else -> "Invalido"
}

fun clasificarEdad(edad: Int): String = when (edad) {
    in 0..12 -> "Nino"
    in 13..19 -> "Adolescente"
    in 20..59 -> "Adulto"
    in 60..150 -> "Adulto mayor"
    else -> "Edad invalida"
}

fun describir(objeto: Any): String = when (objeto) {
    is String -> "Cadena: " + objeto
    is Int -> "Numero: " + objeto
    is Double -> "Decimal: " + objeto
    is Boolean -> "Booleano: " + objeto
    else -> "Desconocido"
}

fun main() {
    println(obtenerDia(3))
    println(clasificarEdad(25))
    println(describir("Hola"))
    println(describir(42))
}