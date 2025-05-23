package br.com.fiap.geomottu.service;

import br.com.fiap.geomottu.dto.filial.FilialDto;
import br.com.fiap.geomottu.dto.filial.FilialGetDto;
import br.com.fiap.geomottu.model.entities.Filial;
import br.com.fiap.geomottu.model.entities.Patio;
import br.com.fiap.geomottu.model.entities.Usuario;
import br.com.fiap.geomottu.repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialService {

    @Autowired
    private FilialRepository repository;

    public Filial save(FilialDto dto) {
        return repository.save(new Filial(dto));
    }

    public List<FilialGetDto> getAll() {
        return repository.findAll().stream().map(FilialGetDto::new).toList();
    }

    public FilialGetDto getId(Long id) {
        Optional<Filial> filial = repository.findById(id);

        if (filial.isPresent()) {
            Filial f = filial.get();
            return new FilialGetDto(f.getId(), f.getNome(), f.getPais(), f.getEndereco(),
                    f.getTelefone(), f.getEmail(), f.getUsuarios()
                    .stream().map(Usuario::getNome).toList(),
                    f.getPatios().stream().map(Patio::getNome).toList());
        } else {
            return null;
        }
    }

    public FilialGetDto update(Long id, FilialGetDto dto) {
        Filial filial = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filial não encontrada"));

        filial.setNome(dto.nome());
        filial.setPais(dto.pais());
        filial.setEndereco(dto.endereco());
        filial.setTelefone(dto.telefone());
        filial.setEmail(dto.email());

        Filial atualizado = repository.save(filial);
        return new FilialGetDto(atualizado);
    }

    public void delete(Long id) {
        Filial filial = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filial não encontrada"));

        repository.delete(filial);
    }
}
