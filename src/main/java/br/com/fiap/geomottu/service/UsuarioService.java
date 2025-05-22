package br.com.fiap.geomottu.service;

import br.com.fiap.geomottu.dto.usuario.UsuarioDto;
import br.com.fiap.geomottu.dto.usuario.UsuarioGetDto;
import br.com.fiap.geomottu.model.entities.Filial;
import br.com.fiap.geomottu.model.entities.Usuario;
import br.com.fiap.geomottu.repository.FilialRepository;
import br.com.fiap.geomottu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private FilialRepository filialRepository;

    public Usuario register(UsuarioDto dto) {
        Filial filial = filialRepository.findById(dto.filialId())
                .orElseThrow(() -> new RuntimeException("Filial não encontrada"));
        return repository.save(new Usuario(dto, filial));
    }

    public UsuarioGetDto getId(Long id) {
        Optional<Usuario> usuario = repository.findById(id);

        if (usuario.isPresent()) {
            Usuario u = usuario.get();
            return new UsuarioGetDto(u.getId(), u.getNome(), u.getSenha() , u.getTipoPerfil(),
                    u.getFilial().getId());
        } else {
            return null;
        }
    }

    public Optional<UsuarioGetDto> getName(String nome) {
        return repository.findByNomeIgnoreCase(nome).map(UsuarioGetDto::new);
    }

    public List<UsuarioGetDto> getAll() {
        return repository.findAll().stream().map(UsuarioGetDto::new).toList();
    }

    public UsuarioGetDto update(Long id, UsuarioGetDto dto) {
        Filial filial = filialRepository.findById(dto.filial())
                .orElseThrow(() -> new RuntimeException("Filial não encontrada"));

        Usuario user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        user.setNome(dto.nome());
        user.setSenha(dto.senha());
        user.setTipoPerfil(dto.tipoPerfil());
        user.setFilial(filial);

        Usuario atualizado = repository.save(user);
        return new UsuarioGetDto(atualizado);
    }

    public void delete(Long id) {
        Usuario user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        repository.delete(user);
    }
}
