package ProgramacionMultimedia

data class Tarea(val id:Int, val descriptor:String, var completada: Boolean=false) {
    fun main(){
        //iniciamos una lista del objeto tarea
        val tareas= mutableListOf<Tarea>()
        var siguienteId=1
        while (true) {
            println("\n=== Gestor de tareas ===")
            println("1. Agregar tarea")
            println("2. Listar tareas")
            println("3. Marcar como completada")
            println("4. Eliminar tarea")
            println("5. Salir")
            print("Opción: ")

            when (readLine()?.toIntOrNull()) {

                1 -> {
                    println("Descripción:")
                    val descriptor = readLine() ?: continue
                    tareas.add(Tarea(siguienteId++, descriptor))
                    println("Tarea agregada.")
                }

                2 -> {
                    if (tareas.isEmpty()) {
                        println("No hay tareas.")
                    } else {
                        tareas.forEach { tarea ->
                            val estado = if (tarea.completada) "[✔]" else "[ ]"
                            println("${tarea.id} - ${tarea.descriptor} $estado")
                        }
                    }
                }

                3 -> {
                    print("ID de la tarea a completar: ")
                    val id = readLine()?.toIntOrNull() ?: continue
                    tareas.find { it.id == id }?.apply {
                        completada = true
                        println("La tarea ha sido completada.")
                    } ?: println("Tarea no encontrada.")
                }

                4 -> {
                    print("ID de la tarea a eliminar: ")
                    val id = readLine()?.toIntOrNull() ?: continue
                    if (tareas.removeIf { it.id == id }) {
                        println("Tarea eliminada.")
                    } else {
                        println("Tarea no encontrada.")
                    }
                }

                5 -> break


            }
        }

    }

}