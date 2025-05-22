package br.com.fiap.geomottu.dto.usuario;

import br.com.fiap.geomottu.model.entities.Usuario;

public record UsuarioGetDto(
        Long id,
        String nome,
        String senha,
        Integer tipoPerfil,
        Long filial
) {
    public UsuarioGetDto(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getSenha(), usuario.getTipoPerfil(), usuario.getFilial().getId());
    }
}
