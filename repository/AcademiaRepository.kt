package repository

import model.Aluno

class AcademiaRepository{
    private val alunosAcademia = mutableListOf<Aluno>(
        Aluno("Ana Souza", "Manhã", 3, false),
        Aluno("Carla Mendes", "Noite", 5, false),
        Aluno("Diego Santos", "Manhã", 4, true),
        Aluno("Felipe Alves", "Noite", 2, false),
        Aluno("Gabriela Rocha", "Manhã", 5, false),
        Aluno("Isabela Castro", "Noite", 4, false),
        Aluno("João Pedro", "Manhã", 2, false)
    )

    fun getAlunos(): List<Aluno> {
        return alunosAcademia.toList()
    }

    fun findByNome(nome: String): Aluno? =
        alunosAcademia.find { it.nome.equals(nome, ignoreCase = true) }

    fun cadastrarAluno(aluno: Aluno): Aluno {
        alunosAcademia.add(aluno)
        return aluno
    }

    fun alterarAluno(nomeAntigo: String, novo: Aluno): Aluno{
        val index = alunosAcademia.indexOfFirst { it.nome.equals(nomeAntigo, ignoreCase = true) }
        alunosAcademia[index] = novo
        return novo
    }

    fun removerAluno(aluno: String): String{
        val alunoRemovido = alunosAcademia.removeIf{it.nome == aluno};
        return if (alunoRemovido) "Aluno removido com sucesso" else "Aluno não encontrado!"
    }

}