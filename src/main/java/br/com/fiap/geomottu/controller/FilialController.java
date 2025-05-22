package br.com.fiap.geomottu.controller;

import br.com.fiap.geomottu.dto.filial.FilialDto;
import br.com.fiap.geomottu.dto.filial.FilialGetDto;
import br.com.fiap.geomottu.model.entities.Filial;
import br.com.fiap.geomottu.repository.FilialRepository;
import br.com.fiap.geomottu.service.FilialService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filial")
public class FilialController {

    @Autowired
    private FilialService service;

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid FilialDto json) {
        service.save(json);
    }

    @GetMapping
    public List<FilialGetDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public FilialGetDto getById(@PathVariable Long id) {
        return service.getId(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public FilialGetDto update(@PathVariable Long id, @RequestBody FilialGetDto dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
