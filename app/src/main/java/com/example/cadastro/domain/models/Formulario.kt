package com.example.cadastro.domain.models

import com.example.cadastro.domain.enums.Estado
import com.example.cadastro.domain.enums.Sexo

class Formulario {
    lateinit var nome: String
    lateinit var telefone: String
    lateinit var email: String
    var listaEmails: Boolean = false
    lateinit var sexo: Sexo
    lateinit var cidade: String
    lateinit var estado: Estado
    override fun toString(): String {
        return "Formulario(nome='$nome', telefone='$telefone', email='$email', listaEmails=$listaEmails, sexo=$sexo, cidade='$cidade', estado=$estado)"
    }
}