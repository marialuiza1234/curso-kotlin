import service.AcademiaService
import java.util.Scanner

/**
 * Atividade de Maria Luiza Mendes Barros
 */
fun main() {
    val academia = AcademiaService()
    val scanner = Scanner(System.`in`)

    println("************ BEM VINDO!! ***********")

    while (true) {
        println()
        println("1 -> Cadastrar")
        println("2 -> Listar")
        println("3 -> Pesquisar")
        println("4 -> Alterar")
        println("5 -> Remover")
        println("6 -> Finalizar")
        print("Escolha uma opção: ")

        val opcao = scanner.nextLine().trim().toIntOrNull()

        if (opcao == null) {
            println("Entrada inválida! Digite um número de 1 a 6.")
            continue
        }
        when (opcao) {
            1 -> academia.cadastrarAluno(scanner)
            2 -> academia.listar(scanner)
            3 -> {
                val mensagem = academia.pesquisarPorNome(scanner)
                println(mensagem)
                academia.pausar(scanner)
            }
            4 -> academia.alterarCadastro(scanner)
            5 -> {
                val mensagem = academia.removeAluno(scanner)
                println(mensagem)
                academia.pausar(scanner)
            }
            6 -> {
                println("Encerrando o sistema... Até mais!")
                scanner.close()
                break
            }
            else -> println("Opção inválida. Digite um número de 1 a 6.")
        }
    }
}
