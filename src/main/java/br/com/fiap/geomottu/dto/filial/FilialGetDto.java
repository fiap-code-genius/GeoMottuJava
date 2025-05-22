package br.com.fiap.geomottu.dto.filial;

import br.com.fiap.geomottu.model.entities.Endereco;
import br.com.fiap.geomottu.model.entities.Filial;
import br.com.fiap.geomottu.model.entities.Usuario;
import br.com.fiap.geomottu.model.enums.PaisesFilial;

import java.util.List;

public record FilialGetDto(
        Long id,
        String nome,
        PaisesFilial pais,
        Endereco endereco,
        String telefone,
        String email,
        List<String> usuarios
) {
    public FilialGetDto(Filial filial) {
        this(filial.getId(), filial.getNome(), filial.getPais(), filial.getEndereco(), filial.getTelefone(),
                filial.getEmail(), filial.getUsuarios().stream().map(Usuario::getNome).toList());
    }
}
