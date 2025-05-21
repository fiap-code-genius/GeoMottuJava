package br.com.fiap.geomottu.controller;

import br.com.fiap.geomottu.dto.filial.FilialDto;
import br.com.fiap.geomottu.model.entities.Filial;
import br.com.fiap.geomottu.repository.FilialRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filial")
public class FilialController {

    @Autowired
    FilialRepository repository;

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid FilialDto json) {
        repository.save(new Filial(json));
    }
}
