package com.example.projetopetshop.Model;

public class Produto {
    private int idProduto;
    private String nomeProduto;
    private Double precProduto;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getPrecProduto() {
        return precProduto;
    }

    public void setPrecProduto(Double precProduto) {
        this.precProduto = precProduto;
    }

}
