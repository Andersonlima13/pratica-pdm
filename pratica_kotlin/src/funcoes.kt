val materiasENotas = mutableMapOf<String, MutableList<Double>>()


fun adicionarDisciplina(materia: String, notas: MutableList<Double> = mutableListOf()): Boolean {
    return materiasENotas.put(materia, notas) == null
}

fun adicionarNota(materia: String, nota: Double): Boolean {
    val notasDaMateria = materiasENotas[materia]

    return if (notasDaMateria != null) {
        notasDaMateria.add(nota)
        true
    } else {
        false
    }
}


fun calcularMedia(notas: List<Double>): Double {
    return if (notas.isNotEmpty()) notas.average() else 0.0
}


fun adicionarVariasNotas(materia: String, vararg notas: Double): Boolean {
    val listaNotas = materiasENotas[materia]
    return if (listaNotas != null) {
        listaNotas.addAll(notas.toList()) // Converte para List<Double> antes de adicionar
        true
    } else {
        false
    }
}



fun mostrarNotas(materia: String) {
    val listaNotas = materiasENotas[materia]

    if (listaNotas == null) {
        println("Matéria '$materia' não encontrada.")
    } else if (listaNotas.isEmpty()) {
        println("Não foi possível mostrar as notas da matéria '$materia'.")
    } else {
        println("Matéria: $materia")
        listaNotas.forEachIndexed { index, nota ->
            println("Nota ${index + 1}: $nota")
        }
        println("\nMédia: ${calcularMedia(listaNotas)}\n")
    }
}

fun main() {
    // 1. Adicionar uma disciplina com atribuição posicional
    adicionarDisciplina("Matemática", mutableListOf(8.5, 7.0, 9.5))

    // 2. Adicionar uma disciplina com atribuição nomeada
    adicionarDisciplina(materia = "História", notas = mutableListOf(6.0, 7.5))

    // 3. Parâmetro notas com valor padrão em adicionarDisciplina já implementado acima.

    // 4. Adicionar uma disciplina sem atribuir valores a notas
    adicionarDisciplina("Química")

    // 5. Adicionar 3 notas para as 3 disciplinas
    adicionarNota("Matemática", 9.0)
    adicionarNota("História", 8.0)
    adicionarNota("Química", 7.5)

    // 6. Mostrar as notas das 3 disciplinas
    mostrarNotas("Matemática")
    mostrarNotas("História")
    mostrarNotas("Química")

    // 7. Adicionar mais uma disciplina
    adicionarDisciplina("Física")

    // 8. Implementação de adicionarVariasNotas já realizada acima.

    // 9. Adicionar 3 notas para a disciplina que acabamos de criar
    adicionarVariasNotas("Física", 7.0, 8.0, 9.0)

    // 10. Mostrar as notas da disciplina recém-criada
    mostrarNotas("Física")

    // 11 e 12. Implementação da função calcularMedia já realizada acima.
    // 13. Função mostrarNotas já exibe a média das disciplinas.

    // 14. Mostrar as notas de uma disciplina específica
    mostrarNotas("Matemática")
}
