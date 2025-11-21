package PracticaKotlin2

fun main() {
    var record = Int.MAX_VALUE
    var jugarOtraVez = true

    while (jugarOtraVez) {
        val numeroAleatorio = (1..100).random()
        var intentos = 0
        var adivinado = false

        println("¡Adivina el número entre 1 y 100!")

        while (!adivinado) {
            print("Ingresa tu intento: ")
            val input = readLine()

            try {
                val intento = input?.toInt()
                if (intento == null || intento !in 1..100) {
                    println("Por favor ingresa un número válido entre 1 y 100")
                    continue
                }

                intentos++

                when {
                    intento < numeroAleatorio -> println("El número es mayor")
                    intento > numeroAleatorio -> println("El número es menor")
                    else -> {
                        adivinado = true
                        println("¡Correcto! Adivinaste en $intentos intentos")

                        if (intentos < record) {
                            record = intentos
                            println("¡Nuevo récord! ($record intentos)")
                        } else {
                            println("Récord actual: $record intentos")
                        }
                    }
                }
            } catch (e: NumberFormatException) {
                println("Entrada inválida. Ingresa un número entero.")
            }
        }

        print("¿Quieres jugar otra vez? (s/n): ")
        jugarOtraVez = readLine()?.equals("s", ignoreCase = true) == true
        println()
    }

    println("¡Gracias por jugar!")
}