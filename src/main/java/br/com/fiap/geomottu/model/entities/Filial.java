package br.com.fiap.geomottu.model.entities;

import br.com.fiap.geomottu.dto.filial.FilialDto;
import br.com.fiap.geomottu.model.enums.PaisesFilial;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TAB_GEOMOTTU_FILIAL")
@SequenceGenerator(name = "filial", sequenceName = "SQ_TAB_GEOMOTTU_FILIAL", allocationSize = 1)
public class Filial {

    @Id @Column(name = "id_filial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filial")
    private Long id;

    @Column(name = "nm_filial", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "pais_filial", nullable = false)
    private PaisesFilial pais;

    @Embedded
    private Endereco endereco;

    @Column(name = "nm_telefone", length = 15)
    private String telefone;

    @Column(name = "ds_email", length = 30)
    private String email;

    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    //Construtores
    public Filial() {}

    public Filial(Long id, String nome, PaisesFilial pais, Endereco endereco, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Filial(Long id, String nome, PaisesFilial pais, Endereco endereco, String telefone, String email, List<Usuario> usuarios) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.usuarios = usuarios;
    }

    public Filial(FilialDto json) {
        this.nome = json.nome();
        this.pais = json.pais();
        this.endereco = new Endereco(json.endereco());
        this.telefone = json.telefone();
        this.email = json.email();
        this.usuarios = json.usuarios();
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PaisesFilial getPais() {
        return pais;
    }

    public void setPais(PaisesFilial pais) {
        this.pais = pais;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
