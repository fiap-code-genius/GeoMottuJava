package br.com.fiap.geomottu.controller;

import br.com.fiap.geomottu.dto.usuario.UsuarioDto;
import br.com.fiap.geomottu.model.entities.Filial;
import br.com.fiap.geomottu.model.entities.Usuario;
import br.com.fiap.geomottu.repository.FilialRepository;
import br.com.fiap.geomottu.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    FilialRepository filialRepository;

    @Transactional
    @PostMapping
    public void post(@RequestBody @Valid UsuarioDto json){
        Filial filial = filialRepository.findById(json.filialId())
                .orElseThrow(() -> new RuntimeException("Filial não encontrada"));
        repository.save(new Usuario(json, filial));
    }
}
