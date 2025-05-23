package br.com.fiap.geomottu.controller;

import br.com.fiap.geomottu.dto.moto.MotoDto;
import br.com.fiap.geomottu.dto.moto.MotoGetDto;
import br.com.fiap.geomottu.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    private MotoService service;

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid MotoDto dto) { service.save(dto); }

    @GetMapping
    public List<MotoGetDto> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public MotoGetDto getById(@PathVariable Long id) { return service.getId(id); }

    @GetMapping("/placa/{placa}")
    public Optional<MotoGetDto> getPlate(@PathVariable String placa) { return service.getPlate(placa); }

    @GetMapping("/chassi/{chassi}")
    public Optional<MotoGetDto> getChassi(@PathVariable String chassi) { return service.getChassi(chassi); }

    @PutMapping("/{id}")
    @Transactional
    public MotoGetDto update(@PathVariable Long id, @RequestBody MotoGetDto dto) { return service.update(id, dto); }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) { service.delete(id); }
}
