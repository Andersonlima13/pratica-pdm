package br.ifpb.pdm.praticas

class Livro(var titulo: String, var preco: Float) {
    override fun toString(): String {
        return "Livro: Titulo = $titulo, Preco = $preco"
    }
}


fun menu() {
    println("1 - Cadastrar livro")
    println("2 - Excluir livro")
    println("3 - Buscar livro")
    println("4 - Editar livro")
    println("5 - Listar livros")
    println("6 - Listar livros que começam com letra escolhida")
    println("7 - Listar livros com preço abaixo do informado")
    println("8 - Sair")
}

fun inputTitulo(): String {
    print("Digite o título do livro: ")
    return readlnOrNull() ?: ""
}

fun inputPreco(): Float {
    print("Digite o preço do livro: ")
    return readlnOrNull()?.toFloatOrNull() ?: 0.0f
}

fun cadastrarLivro(repositorio: MutableList<Livro>) {
    val titulo = inputTitulo()
    val preco = inputPreco()
    repositorio.add(Livro(titulo, preco))
    println("\nLivro cadastrado com sucesso!\n")
}

fun excluirLivro(repositorio: MutableList<Livro>) {
    val livro = buscarNome(repositorio)
    if (livro != null) {
        repositorio.remove(livro)
        println("Livro removido com sucesso!")
    } else {
        println("Livro não encontrado.")
    }
}

fun buscarNome(repositorio: MutableList<Livro>): Livro? {
    val titulo = inputTitulo()
    return repositorio.find { it.titulo.equals(titulo, ignoreCase = true) }
}

fun editarLivro(repositorio: MutableList<Livro>) {
    val livro = buscarNome(repositorio)
    if (livro != null) {
        println("Livro encontrado: $livro")
        val novoTitulo = inputTitulo()
        val novoPreco = inputPreco()

        livro.titulo = if (novoTitulo.isNotEmpty()) novoTitulo else livro.titulo
        livro.preco = if (novoPreco > 0) novoPreco else livro.preco
        println("Livro atualizado com sucesso: $livro")
    } else {
        println("Livro não encontrado.")
    }
}

fun listar(repositorio: MutableList<Livro>) {
    if (repositorio.isNotEmpty()) {
        println("Lista de Livros:")
        repositorio.forEach { println(it) }
    } else {
        println("Nenhum livro cadastrado.")
    }
}

fun listarComLetraInicial(repositorio: MutableList<Livro>) {
    print("Informe a letra: ")
    var letra = readlnOrNull() ?: ""

    while (letra.length != 1) {
        print("Informe apenas uma letra: ")
        letra = readlnOrNull() ?: ""
    }

    val livros = repositorio.filter { it.titulo.startsWith(letra, ignoreCase = true) }
    if (livros.isNotEmpty()) {
        livros.forEach { println(it) }
    } else {
        println("Nenhum livro encontrado com essa letra inicial.")
    }
}

fun listarComPrecoAbaixo(repositorio: MutableList<Livro>) {
    val preco = inputPreco()
    val livros = repositorio.filter { it.preco < preco }
    if (livros.isNotEmpty()) {
        livros.forEach { println(it) }
    } else {
        println("Nenhum livro encontrado abaixo desse preço.")
    }
}

fun main() {
    val repositorioLivros = mutableListOf(
        Livro("Livro dos Livros", 999999.99f),
        Livro("Turma da Monica", 4.99f),
        Livro("Kotlin for Dummies", 29.99f),
        Livro("A", 59.99f)
    )

    var opcao = 0
    while (opcao != 8) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toIntOrNull() ?: 8

        when (opcao) {
            1 -> cadastrarLivro(repositorioLivros)
            2 -> excluirLivro(repositorioLivros)
            3 -> {
                val livro = buscarNome(repositorioLivros)
                println(livro ?: "Livro não encontrado.")
            }
            4 -> editarLivro(repositorioLivros)
            5 -> listar(repositorioLivros)
            6 -> listarComLetraInicial(repositorioLivros)
            7 -> listarComPrecoAbaixo(repositorioLivros)
            8 -> println("Até a próxima :)")
            else -> println("Opção inválida!")
        }
        Thread.sleep(1000)
    }
}
