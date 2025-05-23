package br.com.fiap.geomottu.dto.patio;

import br.com.fiap.geomottu.model.entities.Moto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PatioDto(
        @Size(max = 50, message = "O nome só pode ter no máximo 50 caracteres")
        String nome,
        @NotNull(message = "A capacidade não pode ser nula")
        Integer capacidadeTotal,
        Long filialId,
        List<Moto> motos
) {
}
