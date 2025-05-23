package br.com.fiap.geomottu.model.entities;

import br.com.fiap.geomottu.model.enums.EstadoMoto;
import br.com.fiap.geomottu.model.enums.TipoMoto;
import jakarta.persistence.*;

@Entity
@Table(name = "TAB_GEOMOTTU_MOTO")
@SequenceGenerator(name = "moto", sequenceName = "SQ_TAB_GEOMOTTU_MOTO", allocationSize = 1)
public class Moto {

    @Id @Column(name = "id_moto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moto")
    private Long id;

    @Column(name = "nr_placa", length = 8, unique = true)
    private String placa;

    @Column(name = "nr_chassi", length = 50, unique = true)
    private String chassi;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_modelo", nullable = false)
    private TipoMoto tipoMoto;

    @Enumerated(EnumType.STRING)
    @Column(name = "st_operacional", nullable = false)
    private EstadoMoto estadoMoto;

    @ManyToOne()
    @JoinColumn(name = "id_patio")
    private Patio patio;

    public Moto() {}

    public Moto(Long id, String placa, String chassi, TipoMoto tipoMoto, EstadoMoto estadoMoto, Patio patio) {
        this.id = id;
        this.placa = placa;
        this.chassi = chassi;
        this.tipoMoto = tipoMoto;
        this.estadoMoto = estadoMoto;
        this.patio = patio;
    }

    public Moto(Long id, String placa, String chassi, TipoMoto tipoMoto, EstadoMoto estadoMoto) {
        this.id = id;
        this.placa = placa;
        this.chassi = chassi;
        this.tipoMoto = tipoMoto;
        this.estadoMoto = estadoMoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public TipoMoto getTipoMoto() {
        return tipoMoto;
    }

    public void setTipoMoto(TipoMoto tipoMoto) {
        this.tipoMoto = tipoMoto;
    }

    public EstadoMoto getEstadoMoto() {
        return estadoMoto;
    }

    public void setEstadoMoto(EstadoMoto estadoMoto) {
        this.estadoMoto = estadoMoto;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }
}
