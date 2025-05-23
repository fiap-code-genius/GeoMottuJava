package br.com.fiap.geomottu.dto.moto;

import br.com.fiap.geomottu.annotation.interfaces.Unique;
import br.com.fiap.geomottu.model.entities.Moto;
import br.com.fiap.geomottu.model.enums.EstadoMoto;
import br.com.fiap.geomottu.model.enums.TipoMoto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MotoDto(
        @Size(min = 7, max = 8, message = "A placa deve conter 8 caracters")
        @Unique(fieldName = "placa", domainClass = Moto.class, message = "Placa já cadastrada")
        String placa,
        @Size(max = 50, message = "O Chassi só pode ter no máximo 50 caractéres")
        @Unique(fieldName = "chassi", domainClass = Moto.class, message = "Chassi já cadastrado")
        String chassi,
        @NotNull
        TipoMoto tipoMoto,
        @NotNull
        EstadoMoto estadoMoto,
        Long patioId
) {
}
