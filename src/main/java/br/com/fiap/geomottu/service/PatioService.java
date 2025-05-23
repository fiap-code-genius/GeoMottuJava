package br.com.fiap.geomottu.service;

import br.com.fiap.geomottu.dto.patio.PatioDto;
import br.com.fiap.geomottu.dto.patio.PatioGetDto;
import br.com.fiap.geomottu.model.entities.Filial;
import br.com.fiap.geomottu.model.entities.Moto;
import br.com.fiap.geomottu.model.entities.Patio;
import br.com.fiap.geomottu.repository.FilialRepository;
import br.com.fiap.geomottu.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PatioService {

    @Autowired
    private PatioRepository repository;

    @Autowired
    private FilialRepository filialRepository;

    public Patio save(PatioDto dto) {
        Filial filial = filialRepository.findById(dto.filialId())
                .orElseThrow(() -> new RuntimeException("Filial não encontrada"));
        return repository.save(new Patio(dto, filial));
    }

    public List<PatioGetDto> getAll() {
        return repository.findAll().stream().map(PatioGetDto::new).toList();
    }

    public PatioGetDto getById(Long id) {
        Optional<Patio> patio = repository.findById(id);

        if (patio.isPresent()) {
            Patio p = patio.get();
            return new PatioGetDto(p.getId(), p.getNome(), p.getCapacidadeTotal(),
                    p.getFilial().getId(), p.getMotos().stream().map(Moto::getPlaca).toList());
        } else {
            return null;
        }
    }

    public PatioGetDto update(Long id, PatioGetDto dto) {
        Filial filial = filialRepository.findById(dto.filialId())
                .orElseThrow(() -> new RuntimeException("Filial não encontrada"));

        Patio patio = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filial não encontrada"));

        patio.setNome(dto.nome());
        patio.setCapacidadeTotal(dto.capacidadeTotal());
        patio.setFilial(filial);

        Patio atualizado = repository.save(patio);
        return new PatioGetDto(atualizado);
    }

    public void delete(Long id) {
        Patio patio = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pátio não encontrado"));

        repository.delete(patio);
    }
}
