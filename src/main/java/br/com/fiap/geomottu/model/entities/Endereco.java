package br.com.fiap.geomottu.model.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

    private String estado;
    private String siglaEstado;
    private String cidade;
    private String rua;
}
