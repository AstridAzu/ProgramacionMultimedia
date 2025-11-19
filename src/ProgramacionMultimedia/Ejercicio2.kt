package ProgramacionMultimedia

class Ejercicio2 {
    fun main(){
        println("== calculadora")

        //pedimos el primer numero
        println("primer numero ")
        val a=readLine()?.toDoubleOrNull()?: return

        //pedimos al usuario
        println("operaciom + , - , / , *")
        val operaciom=readLine() ?: return

        //pedimos el segundo numero
        println("segundo numero: ")
        val b=readLine()?.toIntOrNull()?: return
    }
}
