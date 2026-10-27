/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.padaria.model;

/**
 *
 * @author igoor
 */
public class Sexualidade {

    //classe de representacao tabela sexualidade

    private int idGenero;
    private String descricao;
    private String sigla;
    private String status; //ativo por padrao no banco

    //Construtor vazio
    public Sexualidade(){
    }

    //Getters e setters

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

        public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //combo bos usa toString() para mostrat o texto de cada item
    @Override
    public String toString(){
        return descricao;
    }
}
