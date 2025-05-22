package br.com.fiap.geomottu.controller;

import br.com.fiap.geomottu.dto.usuario.UsuarioDto;
import br.com.fiap.geomottu.dto.usuario.UsuarioGetDto;
import br.com.fiap.geomottu.model.entities.Filial;
import br.com.fiap.geomottu.model.entities.Usuario;
import br.com.fiap.geomottu.repository.FilialRepository;
import br.com.fiap.geomottu.repository.UsuarioRepository;
import br.com.fiap.geomottu.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Transactional
    @PostMapping
    public void post(@RequestBody @Valid UsuarioDto json){
        service.register(json);
    }

    @GetMapping
    public List<UsuarioGetDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/nome/{nome}")
    public Optional<UsuarioGetDto> getByName(@PathVariable String nome) {
        return service.getName(nome);
    }

    @GetMapping("/{id}")
    public UsuarioGetDto getById(@PathVariable Long id) {
        return service.getId(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public UsuarioGetDto update(@PathVariable Long id, @RequestBody @Valid UsuarioGetDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
