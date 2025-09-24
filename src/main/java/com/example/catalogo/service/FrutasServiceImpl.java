package com.example.catalogo.service;


import com.example.catalogo.dto.FrutasRequestDTO;
import com.example.catalogo.dto.FrutasResponseDTO;
import com.example.catalogo.exception.ResourceNotFoundException;
import com.example.catalogo.model.FrutasModel;
import com.example.catalogo.model.Tipos;
import com.example.catalogo.repository.FrutasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FrutasServiceImpl implements FrutasService {

    @Autowired
    private FrutasRepository repo;

    private FrutasResponseDTO toDTO(FrutasModel e) {
        FrutasResponseDTO r = new FrutasResponseDTO();
        r.setId(e.getId()); r.setNome(e.getNome()); r.setTipo(e.getTipo());
        r.setDescricao(e.getDescricao()); r.setResumopoder(e.getResumoPoder());
        r.setUsuarioAtual(e.getUsuarioAtual()); r.setUsuarioAnterior(e.getUsuarioAnterior());
        r.setDataDescoberta(e.getDataDescoberta()); r.setRaro(e.isRaro());
        return r;
    }
    private void apply(FrutasModel e, FrutasRequestDTO dto) {
        e.setNome(dto.getNome()); e.setTipo(dto.getTiposModel()); e.setDescricao(dto.getDescricao());
        e.setResumoPoder(dto.getResumoPoder()); e.setUsuarioAtual(dto.getUsuarioAtual()); e.setRaro(dto.isRaro());
        if (e.getDataDescoberta() == null) e.setDataDescoberta(LocalDateTime.now());
    }

    @Override
    public FrutasResponseDTO create(FrutasRequestDTO dto) {
        repo.findByNomeIgnoreCase(dto.getNome()).ifPresent(s -> { throw new IllegalArgumentException("Fruta já existe"); });
        FrutasModel e = new FrutasModel();
        apply(e, dto);
        return toDTO(repo.save(e));
    }
    @Override
    public FrutasResponseDTO update(Long id, FrutasRequestDTO dto) {
        FrutasModel e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fruta não encontrada"));
        apply(e, dto);
        return toDTO(repo.save(e));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Fruta não encontrada");
        repo.deleteById(id);
    }

    @Override
    public FrutasResponseDTO getById(Long id) {
        return repo.findById(id).map(this::toDTO).orElseThrow(() -> new ResourceNotFoundException("Fruta não encontrada"));
    }

    @Override
    public Page<FrutasResponseDTO> listAll(Pageable pageable, Tipos tipo, String nomeContains) {
        Page<FrutasModel> page;
        if (tipo != null) page = repo.findByTipo(tipo, pageable);
        else if (nomeContains != null && !nomeContains.isBlank()) page = repo.findByNomeContainingIgnoreCase(nomeContains, pageable);
        else page = repo.findAll(pageable);
        return page.map(this::toDTO);
    }

    @Override
    public List<FrutasResponseDTO> listRaras() {
        return repo.findByRaroTrue().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public FrutasResponseDTO transferirUsuario(Long id, String novoUsuario) {
        FrutasModel e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fruta não encontrada"));
        e.setUsuarioAnterior(e.getUsuarioAtual());
        e.setUsuarioAtual(novoUsuario);
        return toDTO(repo.save(e));
    }
}
