package com.example.brendon.registrodealunosv1.Models;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Aluno_Diplomado {

    @Id
    public long id;
    private String nome;
    private String curso;
    private String faculdade;
    private boolean diplomado = false;

    Aluno_Diplomado() { }

    public Aluno_Diplomado(String nome, String curso, String faculdade, boolean diplomado) {
        this.nome = nome;
        this.curso = curso;
        this.faculdade = faculdade;
        this.diplomado = diplomado;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public String getFaculdade() {
        return faculdade;
    }

    public boolean isDiplomado() {
        return diplomado;
    }
}
