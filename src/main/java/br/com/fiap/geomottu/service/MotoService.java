package br.com.fiap.geomottu.service;

import br.com.fiap.geomottu.dto.moto.MotoDto;
import br.com.fiap.geomottu.dto.moto.MotoGetDto;
import br.com.fiap.geomottu.model.entities.Moto;
import br.com.fiap.geomottu.model.entities.Patio;
import br.com.fiap.geomottu.repository.MotoRepository;
import br.com.fiap.geomottu.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MotoService {

    @Autowired
    private MotoRepository repository;

    @Autowired
    private PatioRepository patioRepository;

    public Moto save(MotoDto dto) {
        Patio patio = patioRepository.findById(dto.patioId())
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado"));
        return repository.save(new Moto(dto, patio));
    }

    public List<MotoGetDto> getAll() {
        return repository.findAll().stream().map(MotoGetDto::new).toList();
    }

    public MotoGetDto getId(Long id) {
        Optional<Moto> moto = repository.findById(id);

        if (moto.isPresent()) {
            Moto m = moto.get();

            return new MotoGetDto(m.getId(), m.getPlaca(), m.getChassi(), m.getTipoMoto(),
                    m.getEstadoMoto(), m.getPatio().getId());
        } else {
            return null;
        }
    }

    public Optional<MotoGetDto> getPlate(String placa) {
        return repository.findByPlacaIgnoreCase(placa).map(MotoGetDto::new);
    }

    public Optional<MotoGetDto> getChassi(String chassi) {
        return repository.findByChassiIgnoreCase(chassi).map(MotoGetDto::new);
    }

    public MotoGetDto update(Long id, MotoGetDto dto) {
        Patio patio = patioRepository.findById(dto.patioId())
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado"));

        Moto moto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        moto.setPlaca(dto.placa());
        moto.setChassi(dto.chassi());
        moto.setTipoMoto(dto.tipoMoto());
        moto.setEstadoMoto(dto.estadoMoto());
        moto.setPatio(patio);

        Moto atualizado = repository.save(moto);

        return new MotoGetDto(atualizado);
    }

    public void delete(Long id) {
        Moto moto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        repository.delete(moto);
    }
}
