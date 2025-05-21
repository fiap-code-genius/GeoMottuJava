package br.com.fiap.geomottu.model.entities;

import br.com.fiap.geomottu.dto.endereco.EnderecoDto;
import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

    private String estado;
    private String siglaEstado;
    private String cidade;
    private String rua;

    public Endereco() {}

    public Endereco(EnderecoDto json){
        this.estado = json.estado();
        this.siglaEstado = json.siglaEstado();
        this.cidade = json.cidade();
        this.rua = json.rua();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }
}
