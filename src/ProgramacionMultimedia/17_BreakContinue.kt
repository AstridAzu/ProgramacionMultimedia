package ProgramacionMultimedia// Break y continue

fun main() {
    // Break
    println("Break:")
    for (i in 1..10) {
        if (i == 5) break
        println(i)
    }
    
    // Continue
    println("\nContinue:")
    for (i in 1..5) {
        if (i == 3) continue
        println(i)
    }
    
    // Break con etiqueta
    println("\nBreak con etiqueta:")
    outer@ for (i in 1..3) {
        for (j in 1..3) {
            if (i == 2 && j == 2) break@outer
            println("(" + i + ", " + j + ")")
        }
    }
}

fun buscarElemento(lista: List<Int>, objetivo: Int): Int {
    for ((indice, valor) in lista.withIndex()) {
        if (valor == objetivo) {
            return indice
        }
    }
    return -1
}