package br.com.fiap.geomottu.model.entities;

import br.com.fiap.geomottu.dto.patio.PatioDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TAB_GEOMOTTU_PATIO")
@SequenceGenerator(name = "patio", sequenceName = "SQ_TAB_GEOMOTTU_PATIO", allocationSize = 1)
public class Patio {

    @Id @Column(name = "id_patio")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patio")
    private Long id;

    @Column(name = "nm_patio", length = 50)
    private String nome;

    @Column(nullable = false, precision = 4, name = "nr_capacidade")
    private Integer capacidadeTotal;

    @ManyToOne
    @JoinColumn(name = "id_filial")
    private Filial filial;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Moto> motos;

    public Patio() {}

    public Patio(Long id, String nome, Integer capacidadeTotal, Filial filial) {
        this.id = id;
        this.nome = nome;
        this.capacidadeTotal = capacidadeTotal;
        this.filial = filial;
    }

    public Patio(Long id, String nome, Integer capacidadeTotal) {
        this.id = id;
        this.nome = nome;
        this.capacidadeTotal = capacidadeTotal;
    }

    public Patio(Long id, String nome, Integer capacidadeTotal, Filial filial, List<Moto> motos) {
        this.id = id;
        this.nome = nome;
        this.capacidadeTotal = capacidadeTotal;
        this.filial = filial;
        this.motos = motos;
    }

    public Patio(Long id, String nome, Integer capacidadeTotal, List<Moto> motos) {
        this.id = id;
        this.nome = nome;
        this.capacidadeTotal = capacidadeTotal;
        this.motos = motos;
    }

    public Patio(PatioDto dto, Filial filial){
        this.nome = dto.nome();
        this.capacidadeTotal = dto.capacidadeTotal();
        this.filial = filial;
        this.motos = dto.motos();
    }

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

    public Integer getCapacidadeTotal() {
        return capacidadeTotal;
    }

    public void setCapacidadeTotal(Integer capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public List<Moto> getMotos() {
        return motos;
    }

    public void setMotos(List<Moto> motos) {
        this.motos = motos;
    }
}
