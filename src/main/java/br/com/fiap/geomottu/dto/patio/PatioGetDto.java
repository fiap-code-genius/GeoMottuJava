package br.com.fiap.geomottu.dto.patio;

import br.com.fiap.geomottu.model.entities.Moto;
import br.com.fiap.geomottu.model.entities.Patio;

import java.util.List;

public record PatioGetDto(
        Long id,
        String nome,
        Integer capacidadeTotal,
        Long filialId,
        List<Long> motos

) {
    public PatioGetDto(Patio patio) {
        this(patio.getId(), patio.getNome(), patio.getCapacidadeTotal(),
                patio.getFilial().getId(), patio.getMotos().stream().map(Moto::getId).toList());
    }
}
