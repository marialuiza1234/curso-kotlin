package service

import model.Aluno
import repository.AcademiaRepository
import java.util.Scanner

class AcademiaService : IAcademiaService{
    private val repository = AcademiaRepository();

    override fun cadastrarAluno(scan: Scanner): Aluno {
        val listaAlunos = repository.getAlunos()
        var novoAluno : Aluno?

        println("Por favor, preencha as informações a seguir: ")
        println("Informe seu nome completo: ")
        var nome = scan.nextLine()

        val alunoExistente = listaAlunos.firstOrNull { it.nome.equals(nome, ignoreCase = true) }
        if (alunoExistente != null) {
            println("Aluno já cadastrado")
            return alunoExistente
        } else {
            println("Informe o turno de preferência: ")
            val horario = scan.nextLine()

            println("Informe a quantidade de dias que irá frequentar a academia: ")
            val quantDias = scan.nextInt()
            scan.nextLine()

            val problemaSaude = run {
                while (true) {
                    print("Você possui algum problema de saúde? S -> SIM / N -> NÃO: ")
                    when (scan.nextLine().trim().uppercase()) {
                        "S" -> return@run true
                        "N" -> return@run false
                        else -> println("Entrada inválida. Use S ou N.")
                    }
                }
                false
            }

            val aluno = Aluno(nome, horario, quantDias, problemaSaude)
            novoAluno = repository.cadastrarAluno(aluno)
            println("Cadastro realizado com sucesso!")
            pausar(scan)
            return novoAluno
        }
    }

    override fun pesquisarPorNome(scan: Scanner): String {
        println("Informe o nome do aluno: ")
        val nome = scan.nextLine().trim()
        val aluno = repository.findByNome(nome)

        return aluno?.let {
            "O aluno ${it.nome} foi encontrado"
        } ?: "O aluno $nome não foi encontrado, por favor realize o cadastro"
    }

    override fun alterarCadastro(scan: Scanner): Aluno? {
        println("Informe o nome do aluno que deseja alterar o cadastro: ")
        val nomeBusca = scan.nextLine()

        println("Validando se o nome existe na nossa base de dados: ")
        val nomeExistente = repository.findByNome(nomeBusca)
        if(nomeExistente == null){
            println("Aluno $nomeBusca não encontrado")
            return null
        }else{
            println("Informe o nome completo para alteração: ")
            var novoNome = scan.nextLine()

            println("Informe o turno de preferência: ")
            val novoHorario = scan.nextLine()

            println("Informe a quantidade de dias que irá frequentar a academia: ")
            val novosDias = scan.nextInt()
            scan.nextLine()

            val novoProblemaSaude = run {
                while (true) {
                    print("Você possui algum problema de saúde? S -> SIM / N -> NÃO: ")
                    when (scan.nextLine().trim().uppercase()) {
                        "S" -> return@run true
                        "N" -> return@run false
                        else -> println("Entrada inválida. Use S ou N.")
                    }
                }
                false
            }

            val alunoAlterado = Aluno(
                nome = novoNome,
                horario = novoHorario,
                quantDias = novosDias,
                problemaSaude = novoProblemaSaude
            )

            val atualizado = repository.alterarAluno(nomeBusca, alunoAlterado)
            println("Ajuste realizado com sucesso!")
            pausar(scan)
            return atualizado

        }
    }

    override fun removeAluno(scan: Scanner): String{
        println("Informe o nome do aluno que deseja remover o cadastro: ")
        val nomeBusca = scan.nextLine()
        val mensagem = repository.removerAluno(nomeBusca)
        return mensagem
    }

    override fun listar(scan: Scanner) {
        val alunos = repository.getAlunos()
        println("Lista de alunos cadastrados:")
        alunos.forEach { aluno -> println("- ${aluno.nome.lowercase()} | " +
                    "Turno: ${aluno.horario.lowercase()} | " +
                    "Quantidade de Dias: ${aluno.quantDias} | " +
                    "Problema de Saúde: ${if (aluno.problemaSaude) "SIM" else "NÃO"}")
        }
        pausar(scan)
    }

    fun pausar(scan: Scanner, mensagem: String = "Pressione Enter para continuar...") {
        print(mensagem)
        scan.nextLine()
    }
}