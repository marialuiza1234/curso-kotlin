package model

class Aluno{
    var nome : String = ""
    var horario : String = ""
    var quantDias : Int = 0
    var problemaSaude : Boolean = false

    constructor(nome : String, horario: String, quantDias: Int, problemaSaude: Boolean){
        this.nome = nome
        this.horario = horario
        this.quantDias = quantDias
        this.problemaSaude = problemaSaude
    }
}