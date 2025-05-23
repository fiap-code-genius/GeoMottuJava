package br.com.fiap.geomottu.controller;

import br.com.fiap.geomottu.dto.patio.PatioDto;
import br.com.fiap.geomottu.dto.patio.PatioGetDto;
import br.com.fiap.geomottu.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patio")
public class PatioController {

    @Autowired
    private PatioService service;

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid PatioDto dto) { service.save(dto); }

    @GetMapping
    public List<PatioGetDto> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public PatioGetDto getById(@PathVariable Long id) { return service.getById(id); }

    @PutMapping("/{id}")
    @Transactional
    public PatioGetDto update(@PathVariable Long id, @RequestBody PatioGetDto dto){ return service.update(id, dto); }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) { service.delete(id); }
}
