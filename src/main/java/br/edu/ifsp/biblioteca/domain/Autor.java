package br.edu.ifsp.biblioteca.domain;

public class Autor {

    private Long id;
    private String nome;

    public Autor(String nome){
        this.nome = nome;
    }

    public Long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
