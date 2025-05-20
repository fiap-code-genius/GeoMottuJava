package br.com.fiap.geomottu.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_GEOMOTTU_USUARIO")
@SequenceGenerator(name = "user", sequenceName = "SQ_TAB_GEOMOTTU_USUARIO", allocationSize = 1)
public class Usuario {

    @Id @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
    private Long id;

    @Column(name = "nm_usuario", unique = true, nullable = false, length = 25)
    private String nome;

    @Column(name = "ds_senha", nullable = false, length = 100)
    private String senha;

    @Column(name = "tp_perfil", nullable = false, precision = 1)
    private Integer tipoPerfil;

    @ManyToOne
    @JoinColumn(name = "id_filial")
    private Filial filial;

    //Construtores
    public Usuario() {}

    public Usuario(String senha, Long id, String nome, Integer tipoPerfil) {
        this.senha = senha;
        this.id = id;
        this.nome = nome;
        this.tipoPerfil = tipoPerfil;
    }

    public Usuario(Long id, String nome, String senha, Integer tipoPerfil, Filial filial) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.tipoPerfil = tipoPerfil;
        this.filial = filial;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(Integer tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
}
