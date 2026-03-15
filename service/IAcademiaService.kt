package service

import model.Aluno
import java.util.Scanner

interface IAcademiaService {
    fun cadastrarAluno(scan: Scanner): Aluno;
    fun pesquisarPorNome(scan: Scanner): String;
    fun alterarCadastro(scan: Scanner): Aluno?;
    fun removeAluno(scan: Scanner): String;
    fun listar(scan: Scanner):Unit
}